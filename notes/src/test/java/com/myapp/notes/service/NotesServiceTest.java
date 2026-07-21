package com.myapp.notes.service;

import com.myapp.notes.dto.CreateNotesRequest;
import com.myapp.notes.dto.NotesResponse;
import com.myapp.notes.model.Notes;
import com.myapp.notes.model.RemarksStatus;
import com.myapp.notes.repository.NotesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class NotesServiceTest {

    @Mock
    private NotesRepository notesRepository;

    @InjectMocks
    private NotesService notesService;

    @Test
    void successfullyCreateNote(){

        UUID patientId = UUID.randomUUID();

        CreateNotesRequest dto = new CreateNotesRequest();
        dto.setPatientId(patientId);
        dto.setRemarks("Ajout de la toute première note d'un patient");

        Notes patientNote = new Notes();
        patientNote.setCreatedAt(LocalDateTime.now());
        patientNote.setStatus(RemarksStatus.ACTIVE);
        patientNote.setRemarks("Ajout de la toute première note d'un patient");
        patientNote.setPatientId(patientId.toString());

        when(notesRepository.save(any(Notes.class))).thenReturn(patientNote);
        NotesResponse result = notesService.createNotes(dto);

        assertThat(result).isNotNull();
        assertThat(result.getRemarks()).isEqualTo("Ajout de la toute première note d'un patient");
        assertThat(result.getPatientId()).isEqualTo(patientId);

        verify(notesRepository).save(any(Notes.class));
    }

    @Test
    void successfullyFindAllNotesByPatientId(){
        String patientId = UUID.randomUUID().toString();

        LocalDateTime createdAt1 = LocalDateTime.of(2026, 1, 15, 10, 30);
        LocalDateTime createdAt2 = LocalDateTime.of(2026, 2, 20, 14, 0);

        Notes note1 = new Notes();
        note1.setNotesId("note-01");
        note1.setPatientId(patientId);
        note1.setRemarks("Cholesterol élevé");
        note1.setCreatedAt(createdAt1);
        note1.setStatus(RemarksStatus.ACTIVE);

        Notes note2 = new Notes();
        note2.setNotesId("note-02");
        note2.setPatientId(patientId);
        note2.setRemarks("Fumeur depuis 10 ans");
        note2.setCreatedAt(createdAt2);
        note2.setStatus(RemarksStatus.RESOLVED);

        when(notesRepository.findByPatientId(patientId)).thenReturn(List.of(note1, note2));

        List<NotesResponse> result = notesService.findAllNotesByPatientId(patientId);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNotesId()).isEqualTo("note-01");
        assertThat(result.get(0).getPatientId()).isEqualTo(UUID.fromString(patientId));
        assertThat(result.get(0).getRemarks()).isEqualTo("Cholesterol élevé");
        assertThat(result.get(1).getNotesId()).isEqualTo("note-02");
        assertThat(result.get(1).getRemarks()).isEqualTo("Fumeur depuis 10 ans");
    }
}
