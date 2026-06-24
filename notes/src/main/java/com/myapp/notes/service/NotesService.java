package com.myapp.notes.service;

import com.myapp.notes.dto.CreateNotesRequest;
import com.myapp.notes.dto.NotesResponse;
import com.myapp.notes.dto.UpdateNotesRequest;
import com.myapp.notes.model.Notes;
import com.myapp.notes.repository.NotesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NotesService {

    private final NotesRepository repository;

    public NotesService(NotesRepository repository){
        this.repository = repository;
    }

    public NotesResponse findNotesById(String notesId){
        Notes notes = repository.findById(notesId).orElseThrow(() -> new RuntimeException("Notes not found"));

        NotesResponse notesResponse = new NotesResponse();
        notesResponse.setRemarks(notes.getRemarks());
        notesResponse.setPatientId(UUID.fromString(notes.getPatientId()));
        notesResponse.setCreatedAt(LocalDateTime.now());

        return notesResponse;
    }

    public List<String> findAllNotesByPatientId(String patientId){
        List<Notes> patientNotes = repository.findByPatientId(patientId);

        List<String> remarks = new ArrayList<>();
        for(int i = 0; i < patientNotes.size(); i++){
            remarks.add(patientNotes.get(i).getRemarks());
        }
        return remarks;
    }

    public List<Notes> findAllNotes(){
        return repository.findAll();
    }

    public NotesResponse createNotes(CreateNotesRequest dto){
        Notes notes = new Notes();
        notes.setCreatedAt(LocalDateTime.now());
        notes.setPatientId(String.valueOf(dto.getPatientId()));
        notes.setRemarks(dto.getRemarks());

        Notes createdNotes = repository.save(notes);

        NotesResponse notesResponse = new NotesResponse();
        notesResponse.setCreatedAt(LocalDateTime.now());
        notesResponse.setPatientId(UUID.fromString(createdNotes.getPatientId()));
        notesResponse.setRemarks(createdNotes.getRemarks());
        notesResponse.setNotesId(createdNotes.getNotesId());

        return notesResponse;
    }

    public NotesResponse updateNotes(String notesId, UpdateNotesRequest dto){
        Notes notesToUpdate = repository.findById(notesId).orElseThrow(() -> new RuntimeException("Notes not found"));
        notesToUpdate.setRemarks(dto.getRemarks());
        notesToUpdate.setPatientId(String.valueOf(dto.getPatientId()));
        notesToUpdate.setCreatedAt(LocalDateTime.now());

        Notes savedNotes = repository.save(notesToUpdate);

        NotesResponse notesResponse = new NotesResponse();
        notesResponse.setRemarks(savedNotes.getRemarks());
        notesResponse.setPatientId(UUID.fromString(savedNotes.getPatientId()));
        notesResponse.setCreatedAt(LocalDateTime.now());

        return notesResponse;
    }

    public void deleteNotes(String notesId){
        repository.deleteById(notesId);
    }
}
