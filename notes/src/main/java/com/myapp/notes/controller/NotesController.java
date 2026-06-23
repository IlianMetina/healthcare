package com.myapp.notes.controller;

import com.myapp.notes.dto.CreateNotesRequest;
import com.myapp.notes.dto.NotesResponse;
import com.myapp.notes.dto.UpdateNotesRequest;
import com.myapp.notes.model.Notes;
import com.myapp.notes.service.NotesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {

    private final NotesService service;

    public NotesController(NotesService service){
        this.service = service;
    }

    @GetMapping("/{notesId}")
    public NotesResponse findNotesById(@PathVariable UUID notesId){
        return service.findNotesById(notesId);
    }

    @GetMapping("/all")
    public List<Notes> findAllNotes(){
        return service.findAllNotes();
    }

    @PostMapping("/create")
    public NotesResponse createNotes(@RequestBody CreateNotesRequest dto){
        return service.createNotes(dto);
    }

    @PutMapping("/update/{notesId}")
    public NotesResponse updateNotes(@PathVariable UUID notesId, @RequestBody UpdateNotesRequest dto){
        return service.updateNotes(notesId, dto);
    }

    @DeleteMapping("/delete/{notesId}")
    public void deleteNotes(@PathVariable UUID notesId){
        service.deleteNotes(notesId);
    }
}
