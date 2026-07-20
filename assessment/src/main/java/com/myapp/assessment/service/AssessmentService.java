package com.myapp.assessment.service;

import com.myapp.assessment.client.NotesClient;
import com.myapp.assessment.client.PatientClient;
import com.myapp.assessment.dto.NotesResponse;
import com.myapp.assessment.dto.PatientResponse;
import com.myapp.assessment.enums.RisksTerms;
import com.myapp.assessment.enums.TriggerTerms;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class AssessmentService {

    private final PatientClient patientClient;
    private final NotesClient notesClient;
    private AssessmentService self;

    public static final String PATIENT_SERVICE = "patientDetailsService";

    public AssessmentService(PatientClient patientClient, NotesClient notesClient){
        this.patientClient = patientClient;
        this.notesClient = notesClient;
    }

    @org.springframework.beans.factory.annotation.Autowired
    public void setSelf(@org.springframework.context.annotation.Lazy AssessmentService self) {
        this.self = self;
    }

    public RisksTerms calculateRisks(String patientId){

        PatientResponse patient = self.getPatientWithResilience(patientId);
        List<NotesResponse> notes = self.getNotesWithResilience(patientId);

        int patientAge = Period.between(patient.getBirthDate(), LocalDate.now()).getYears();
        TriggerTerms[] terms = TriggerTerms.values();

        int count = 0;
        for(int i = 0; i < terms.length; i++){
            for(int j = 0; j < notes.size(); j++){
                if(notes.get(j).getRemarks().toLowerCase().contains(terms[i].getTerms().toLowerCase())){
                    count++;
                    break;
                }
            }
        }

        if(count == 0) return RisksTerms.NONE;

        if(patientAge > 30 && count >= 8){
            return RisksTerms.EARLY_ONSET;
        }

        if(patient.getGender().equals("F") && patientAge < 30 && count >= 7) {
            return RisksTerms.EARLY_ONSET;
        }

        if(patient.getGender().equals("M") && patientAge < 30 && count >= 5) {
            return RisksTerms.EARLY_ONSET;
        }

        if(patientAge > 30 && count >= 6){
            return RisksTerms.IN_DANGER;
        }

        if(patientAge > 30 && count >= 2) {
            return RisksTerms.BORDERLINE;
        }

        if(patient.getGender().equals("M") && patientAge < 30 && count >= 3){
            return RisksTerms.IN_DANGER;
        }

        if(patient.getGender().equals("F") && patientAge < 30 && count >= 4){
            return RisksTerms.IN_DANGER;
        }

        return RisksTerms.NONE;
    }

    @Retry(name = "retryForPatientDetails")
    @CircuitBreaker(name = PATIENT_SERVICE, fallbackMethod = "getPatientFallback")
    public PatientResponse getPatientWithResilience(String patientId){
        return patientClient.findPatientById(patientId);
    }

    @Retry(name = "retryForPatientDetails")
    @CircuitBreaker(name = PATIENT_SERVICE, fallbackMethod = "getNotesFallback")
    public List<NotesResponse> getNotesWithResilience(String patientId){
        return notesClient.findAllNotesByPatientId(patientId);
    }

    private PatientResponse getPatientFallback(String patientId, Throwable throwable){
        System.err.println("=== PATIENT FALLBACK ACTIVÉ ===");
        System.err.println("Patient ID: " + patientId);
        System.err.println("Type d'erreur: " + throwable.getClass().getName());
        System.err.println("Message: " + throwable.getMessage());
        throw new RuntimeException("Patient service unavailable", throwable);
    }

    private List<NotesResponse> getNotesFallback(String patientId, Throwable throwable){
        System.err.println("=== NOTES FALLBACK ACTIVÉ ===");
        System.err.println("Patient ID: " + patientId);
        System.err.println("Type d'erreur: " + throwable.getClass().getName());
        System.err.println("Message: " + throwable.getMessage());
        throw new RuntimeException("Notes service unavailable", throwable);
    }

}
