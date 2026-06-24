package com.myapp.assessment.controller;

import com.myapp.assessment.enums.RisksTerms;
import com.myapp.assessment.service.AssessmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/assessment")
public class AssessmentController {

    private final AssessmentService service;

    public AssessmentController(AssessmentService service){
        this.service = service;
    }

    @GetMapping("/patient/{patientId}")
    public RisksTerms getRisks(@PathVariable String patientId){
        return service.calculateRisks(patientId);
    }

    @GetMapping("/test")
    public String test(){
        return "Test réussi";
    }

}
