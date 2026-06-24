package com.myapp.notes.repository;

import com.myapp.notes.model.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotesRepository extends MongoRepository<Notes, String> {

    List<Notes> findByPatientId(String patientId);
}
