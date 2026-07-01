package com.myapp.assessment.client;

import com.myapp.assessment.dto.PatientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient")
public interface PatientClient {

    @GetMapping("/api/v1/patients/{patientId}")
    PatientResponse findPatientById(@PathVariable String patientId);
}