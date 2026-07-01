package com.myapp.assessment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "notes")
public interface NotesClient {

    @GetMapping("/api/v1/notes/patient/{patientId}")
    public List<String> findAllNotesByPatientId(@PathVariable String patientId);
}
