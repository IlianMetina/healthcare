package com.myapp.patient.service;

import com.myapp.patient.dto.CreatePatientRequest;
import com.myapp.patient.dto.PatientResponse;
import com.myapp.patient.dto.PutUpdatePatientRequest;
import com.myapp.patient.model.Patient;
import com.myapp.patient.repository.PatientRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository){
        this.repository = repository;
    }

    public PatientResponse findPatientById(UUID patientId){
        Patient patient = repository.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setPatientId(patient.getPatientId());
        patientResponse.setBirthDate(patient.getBirthDate());
        patientResponse.setAddress(patient.getAddress());
        patientResponse.setGender(patient.getGender());
        patientResponse.setPhoneNumber(patient.getPhoneNumber());
        patientResponse.setFirstName(patient.getFirstName());
        patientResponse.setLastName(patient.getLastName());

        return patientResponse;
    }

    public List<Patient> findAllPatients(){
        return repository.findAll();
    }

    public List<Patient> findAllPatientsByDoctorId(Long doctorId){
        return repository.findAllByDoctorsIdContaining(doctorId);
    }

    public PatientResponse createPatient(CreatePatientRequest dto, Jwt jwt){
        Long doctorId = ((Number) jwt.getClaim("id")).longValue();

        Patient patient = new Patient();
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setAddress(dto.getAddress());
        patient.setGender(dto.getGender());
        patient.setLastName(dto.getLastName());
        patient.setFirstName(dto.getFirstName());
        patient.setBirthDate(dto.getBirthDate());
        patient.setDoctorsId(List.of(doctorId));


        Patient createdPatient = repository.save(patient);
        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setPhoneNumber(createdPatient.getPhoneNumber());
        patientResponse.setAddress(createdPatient.getAddress());
        patientResponse.setGender(createdPatient.getGender());
        patientResponse.setLastName(createdPatient.getLastName());
        patientResponse.setFirstName(createdPatient.getFirstName());
        patientResponse.setBirthDate(createdPatient.getBirthDate());
        patientResponse.setPatientId(createdPatient.getPatientId());

        return patientResponse;
    }


    public PatientResponse updatePatient(UUID patientId, PutUpdatePatientRequest dto){
        Patient patientToUpdate = repository.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));
        patientToUpdate.setFirstName(dto.getFirstName());
        patientToUpdate.setLastName(dto.getLastName());
        patientToUpdate.setBirthDate(dto.getBirthDate());
        patientToUpdate.setGender(dto.getGender());
        patientToUpdate.setAddress(dto.getAddress());
        patientToUpdate.setPhoneNumber(dto.getPhoneNumber());

        Patient savedPatient = repository.save(patientToUpdate);

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setPatientId(savedPatient.getPatientId());
        patientResponse.setFirstName(savedPatient.getFirstName());
        patientResponse.setLastName(savedPatient.getLastName());
        patientResponse.setBirthDate(savedPatient.getBirthDate());
        patientResponse.setGender(savedPatient.getGender());
        patientResponse.setAddress(savedPatient.getAddress());
        patientResponse.setPhoneNumber(savedPatient.getPhoneNumber());

        return patientResponse;
    }

    public void deletePatient(UUID patientId){
        repository.deleteById(patientId);
    }
}
