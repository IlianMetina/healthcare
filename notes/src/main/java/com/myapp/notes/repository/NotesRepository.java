package com.myapp.notes.repository;

import com.myapp.notes.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface NotesRepository extends MongoRepository<Notes, String> {
}
