package com.myapp.notes.controller;

import com.myapp.notes.dto.CreateNotesRequest;
import com.myapp.notes.dto.NotesResponse;
import com.myapp.notes.dto.UpdateNotesRequest;
import com.myapp.notes.model.Notes;
import com.myapp.notes.service.NotesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {

    private final NotesService service;

    public NotesController(NotesService service){
        this.service = service;
    }

    @GetMapping("/{notesId}")
    public NotesResponse findNotesById(@PathVariable String notesId){
        return service.findNotesById(notesId);
    }

    @GetMapping("/{patientId}")
    public List<String> findAllRelevantNotesByPatientId(@PathVariable String patientId){
        return service.findAllRelevantNotesByPatientId(patientId);
    }

    @GetMapping("/patient/{patientId}")
    public List<String> findAllNotesByPatientId(@PathVariable String patientId){
        return service.findAllNotesByPatientId(patientId);
    }

    @GetMapping("/all")
    public List<Notes> findAllNotes(){
        return service.findAllNotes();
    }

    @PostMapping("/create")
    public NotesResponse createNotes(@RequestBody CreateNotesRequest dto){

        System.out.println("Notes récupérées :");
        System.out.println(dto.getRemarks());
        return service.createNotes(dto);
    }

    @PutMapping("/update/{notesId}")
    public NotesResponse updateNotes(@PathVariable String notesId, @RequestBody UpdateNotesRequest dto){
        return service.updateNotes(notesId, dto);
    }

    @PatchMapping("/status/{notesId}")
    public NotesResponse updateNotesStatus(@PathVariable String notesId){
        return service.updateNotesStatus(notesId);
    }

    @DeleteMapping("/delete/{notesId}")
    public void deleteNotes(@PathVariable String notesId){
        service.deleteNotes(notesId);
    }
}
