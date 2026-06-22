package com.myapp.patient.service;

import com.myapp.patient.model.Patient;
import com.myapp.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository){
        this.repository = repository;
    }

    public Patient findPatientById(UUID patientId){
        return repository.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public List<Patient> findAllPatients(){
        return repository.findAll();
    }

    public Patient createPatient(Patient patient){
        return repository.save(patient);
    }

    public Patient updatePatient(UUID patientId, Patient patient){
        Patient patientToUpdate = repository.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));
        patientToUpdate.setFirstName(patient.getFirstName());
        patientToUpdate.setLastName(patient.getLastName());
        patientToUpdate.setBirthDate(patient.getBirthDate());
        patientToUpdate.setGender(patient.getGender());
        patientToUpdate.setAddress(patient.getAddress());
        patientToUpdate.setPhoneNumber(patient.getPhoneNumber());

        return repository.save(patientToUpdate);
    }

    public void deletePatient(UUID patientId){
        repository.deleteById(patientId);
    }
}
