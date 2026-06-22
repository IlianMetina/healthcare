package com.myapp.patient.controller;

import com.myapp.patient.model.Patient;
import com.myapp.patient.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service){
        this.service = service;
    }

    @GetMapping("/{patientId}")
    public Patient findPatientById(@PathVariable UUID patientId){
        return service.findPatientById(patientId);
    }

    @GetMapping("all")
    public List<Patient> findAllPatients(){
        return service.findAllPatients();
    }

    @PostMapping("create")
    public Patient createPatient(@RequestBody Patient patient){
        return service.createPatient(patient);
    }

    // Modifier nom variables patient pour indiquer plus clairement que c'est
    // un patient à modifier
    @PutMapping("update/{patientId}")
    public Patient updatePatient(@PathVariable UUID patientId, @RequestBody Patient patient){
        return service.updatePatient(patientId, patient);
    }

    @DeleteMapping("delete/{patientId}")
    public void deletePatient(@PathVariable UUID patientId){
        service.deletePatient(patientId);
    }
}
