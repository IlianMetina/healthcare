package com.myapp.assessment.service;

import com.myapp.assessment.client.NotesClient;
import com.myapp.assessment.client.PatientClient;
import com.myapp.assessment.dto.NotesResponse;
import com.myapp.assessment.dto.PatientResponse;
import com.myapp.assessment.enums.RisksTerms;
import com.myapp.assessment.enums.TriggerTerms;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class AssessmentService {

    private final PatientClient patientClient;
    private final NotesClient notesClient;

    public AssessmentService(PatientClient patientClient, NotesClient notesClient){
        this.patientClient = patientClient;
        this.notesClient = notesClient;
    }

    public RisksTerms calculateRisks(String patientId){

        PatientResponse patient = patientClient.findPatientById(patientId);
        List<NotesResponse> notes = notesClient.findAllNotesByPatientId(patientId);

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
}
