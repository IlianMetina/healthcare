package com.myapp.patient.service;

import com.myapp.patient.dto.CreatePatientRequest;
import com.myapp.patient.dto.PatientResponse;
import com.myapp.patient.dto.PutUpdatePatientRequest;
import com.myapp.patient.model.Gender;
import com.myapp.patient.model.Patient;
import com.myapp.patient.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static com.myapp.patient.util.JwtTest.fakeJwt;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    void successfullyCreatePatient(){
        UUID patientId = UUID.randomUUID();
        Jwt jwt = fakeJwt(1L);

        CreatePatientRequest dto = new CreatePatientRequest();
        dto.setFirstName("Jean");
        dto.setLastName("Valjean");
        dto.setPhoneNumber("0625471885");
        dto.setAddress("131 Rue de la République");
        dto.setGender(Gender.M);
        dto.setBirthDate(LocalDate.of(1990, 5, 12));

        Patient savedPatient = new Patient();
        savedPatient.setPatientId(patientId);
        savedPatient.setFirstName("Jean");
        savedPatient.setLastName("Valjean");
        savedPatient.setPhoneNumber("0625471885");
        savedPatient.setAddress("131 Rue de la République");
        savedPatient.setGender(Gender.M);
        savedPatient.setBirthDate(LocalDate.of(1990, 5, 12));
        savedPatient.setDoctorsId(List.of(1L));

        when(patientRepository.save(any(Patient.class))).thenReturn(savedPatient);
        PatientResponse result = patientService.createPatient(dto, jwt);

        assertThat(result.getPatientId()).isEqualTo(patientId);
        assertThat(result.getFirstName()).isEqualTo("Jean");
        assertThat(result.getLastName()).isEqualTo("Valjean");
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    void successfullyUpdatedPatient(){

        UUID patientId = UUID.randomUUID();

        Patient existingPatient = new Patient();
        existingPatient.setPatientId(patientId);
        existingPatient.setFirstName("Jean");
        existingPatient.setLastName("Valjean");
        existingPatient.setBirthDate(LocalDate.of(1990, 5, 12));
        existingPatient.setGender(Gender.M);
        existingPatient.setPhoneNumber("0660066006");
        existingPatient.setAddress("131 Ancienne Adresse");

        PutUpdatePatientRequest updatedPatient = new PutUpdatePatientRequest();
        updatedPatient.setFirstName("Jean");
        updatedPatient.setLastName("Valjean");
        updatedPatient.setBirthDate(LocalDate.of(1990, 5, 12));
        updatedPatient.setGender(Gender.M);
        updatedPatient.setPhoneNumber("0660606060");
        updatedPatient.setAddress("156 Nouvelle Adresse");

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(existingPatient));
        when(patientRepository.save(any(Patient.class))).thenAnswer(inv -> inv.getArgument(0));

        PatientResponse result = patientService.updatePatient(patientId, updatedPatient);

        assertThat(result.getAddress()).isEqualTo("156 Nouvelle Adresse");
        assertThat(result.getPhoneNumber()).isEqualTo("0660606060");
        assertThat(result.getFirstName()).isEqualTo("Jean");
        assertThat(result.getLastName()).isEqualTo("Valjean");

        verify(patientRepository, times(1)).findById(patientId);
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    void successfullyDeletedPatient(){
        UUID patientId = UUID.randomUUID();
        doNothing().when(patientRepository).deleteById(patientId);
        patientService.deletePatient(patientId);
        verify(patientRepository, times(1)).deleteById(patientId);
    }
}
