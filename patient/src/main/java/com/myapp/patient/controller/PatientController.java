package com.myapp.patient.controller;

import com.myapp.patient.dto.CreatePatientRequest;
import com.myapp.patient.dto.PatientResponse;
import com.myapp.patient.dto.UpdatePatientRequest;
import com.myapp.patient.model.Patient;
import com.myapp.patient.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service){
        this.service = service;
    }

    @GetMapping("/{patientId}")
    public PatientResponse findPatientById(@PathVariable UUID patientId){
        return service.findPatientById(patientId);
    }

    @GetMapping("/all")
    public List<Patient> findAllPatients(){
        return service.findAllPatients();
    }

    @PostMapping("/create")
    public PatientResponse createPatient(@RequestBody CreatePatientRequest dto){
        return service.createPatient(dto);
    }

    // Modifier nom variables patient pour indiquer plus clairement que c'est
    // un patient à modifier
    @PutMapping("/update/{patientId}")
    public PatientResponse updatePatient(@PathVariable UUID patientId, @RequestBody UpdatePatientRequest dto){
        return service.updatePatient(patientId, dto);
    }

    @DeleteMapping("/delete/{patientId}")
    public void deletePatient(@PathVariable UUID patientId){
        service.deletePatient(patientId);
    }
}
