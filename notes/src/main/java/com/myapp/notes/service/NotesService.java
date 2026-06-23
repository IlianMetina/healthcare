package com.myapp.notes.service;

import com.myapp.notes.dto.CreateNotesRequest;
import com.myapp.notes.dto.NotesResponse;
import com.myapp.notes.dto.UpdateNotesRequest;
import com.myapp.notes.model.Notes;
import com.myapp.notes.repository.NotesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotesService {

    private final NotesRepository repository;

    public NotesService(NotesRepository repository){
        this.repository = repository;
    }

    public NotesResponse findNotesById(UUID notesId){
        Notes notes = repository.findById(notesId).orElseThrow(() -> new RuntimeException("Notes not found"));

        NotesResponse notesResponse = new NotesResponse();
        notesResponse.setRemarks(notes.getRemarks());
        notesResponse.setPatientId(notes.getPatientId());
        notesResponse.setCreatedAt(notes.getCreatedAt());

        return notesResponse;
    }

    public List<Notes> findAllNotes(){
        return repository.findAll();
    }

    public NotesResponse createNotes(CreateNotesRequest dto){
        Notes notes = new Notes();
        notes.setCreatedAt(dto.getCreatedAt());
        notes.setPatientId(dto.getPatientId());
        notes.setRemarks(dto.getRemarks());

        Notes createdNotes = repository.save(notes);

        NotesResponse notesResponse = new NotesResponse();
        notesResponse.setCreatedAt(createdNotes.getCreatedAt());
        notesResponse.setPatientId(createdNotes.getPatientId());
        notesResponse.setRemarks(createdNotes.getRemarks());

        return notesResponse;
    }

    public NotesResponse updateNotes(UUID notesId, UpdateNotesRequest dto){
        Notes notesToUpdate = repository.findById(notesId).orElseThrow(() -> new RuntimeException("Notes not found"));
        notesToUpdate.setRemarks(dto.getRemarks());
        notesToUpdate.setPatientId(dto.getPatientId());
        notesToUpdate.setCreatedAt(dto.getCreatedAt());

        Notes savedNotes = repository.save(notesToUpdate);

        NotesResponse notesResponse = new NotesResponse();
        notesResponse.setRemarks(savedNotes.getRemarks());
        notesResponse.setPatientId(savedNotes.getPatientId());
        notesResponse.setCreatedAt(savedNotes.getCreatedAt());

        return notesResponse;
    }

    public void deleteNotes(UUID notesId){
        repository.deleteById(notesId);
    }
}
