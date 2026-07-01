package com.myapp.patient.controller;

import com.myapp.patient.dto.CreatePatientRequest;
import com.myapp.patient.dto.PatientResponse;
import com.myapp.patient.dto.PutUpdatePatientRequest;
import com.myapp.patient.model.Patient;
import com.myapp.patient.service.PatientService;
import jakarta.validation.Valid;
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

    @GetMapping("/{doctorId}")
    public List<Patient> findAllPatientsByDoctorId(@PathVariable Long doctorId){
        return service.
    }

    @GetMapping("/all")
    public List<Patient> findAllPatients(){
        return service.findAllPatients();
    }

    @PostMapping("/create")
    public PatientResponse createPatient(@Valid @RequestBody CreatePatientRequest dto){
        return service.createPatient(dto);
    }

    // Modifier nom variables patient pour indiquer plus clairement que c'est
    // un patient à modifier
    @PutMapping("/update/{patientId}")
    public PatientResponse updatePatient(@PathVariable UUID patientId, @Valid @RequestBody PutUpdatePatientRequest dto){
        return service.updatePatient(patientId, dto);
    }

    @DeleteMapping("/delete/{patientId}")
    public void deletePatient(@PathVariable UUID patientId){
        service.deletePatient(patientId);
    }
}
