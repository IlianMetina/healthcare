package com.myapp.assessment.service;

import com.myapp.assessment.client.NotesClient;
import com.myapp.assessment.client.PatientClient;
import com.myapp.assessment.dto.NotesResponse;
import com.myapp.assessment.dto.PatientResponse;
import static org.assertj.core.api.Assertions.*;
import com.myapp.assessment.enums.RisksTerms;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
public class AssessmentServiceTest {

    private AssessmentService assessmentService;

    private PatientClient patientClient;
    private NotesClient notesClient;

    @BeforeEach
    void setUp(){
        AssessmentService realService = new AssessmentService(patientClient, notesClient);
        assessmentService = spy(realService);
        ReflectionTestUtils.setField(assessmentService, "self", assessmentService);
    }

    @Test
    void calculateRiskShouldReturnNone(){
        String patientId = "patient-123";

        PatientResponse patient = new PatientResponse();
        patient.setBirthDate(LocalDate.now().minusYears(45));
        patient.setGender("F");

        NotesResponse note = new NotesResponse();
        note.setRemarks("Consultation banale, RAS");

        doReturn(patient).when(assessmentService).getPatientWithResilience(patientId);
        doReturn(List.of(note)).when(assessmentService).getNotesWithResilience(patientId);

        RisksTerms result = assessmentService.calculateRisks(patientId);

        assertThat(result).isEqualTo(RisksTerms.NONE);
    }
}
