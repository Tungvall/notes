package se.twowall.notes.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import se.twowall.notes.dto.NoteRequest;
import se.twowall.notes.dto.NoteResponse;
import se.twowall.notes.entity.Note;
import se.twowall.notes.entity.User;
import se.twowall.notes.repository.NoteRepository;
import se.twowall.notes.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class NoteService {
    private final UserRepository userRepository;
    public NoteRepository noteRepository;


    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }


    public NoteResponse fetchNoteBy(Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Note note = noteRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(() -> new RuntimeException("Error: username not found"));

        return new NoteResponse(
                note.getId(),
                note.getNoteTitle(),
                note.getNoteContent(),
                note.getCreatedAt(),
                note.getUpdatedAt());
    }

    public NoteResponse getNoteByID(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            throw new RuntimeException("Access denied");
        }

        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        Note note;

        if (isAdmin) {
            note = noteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Error: username not found"));
        } else {
            note = noteRepository.findByIdAndUserUsername(id, username)
                    .orElseThrow(() -> new RuntimeException("Error: username not found"));
        }

        return new NoteResponse(
                note.getId(),
                note.getNoteTitle(),
                note.getNoteContent(),
                note.getCreatedAt(),
                note.getUpdatedAt());

    }

    public boolean updateNoteByID(Long id, NoteRequest noteRequest) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new RuntimeException("Access denied");
        }

        String username = auth.getName();

        Note note = noteRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(() -> new RuntimeException("Error: username not found"));

        note.setNoteTitle(noteRequest.getNoteTitle());
        note.setNoteContent(noteRequest.getNoteContent());

        noteRepository.save(note);
        return true;
    }

    public boolean deleteNoteByID(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            throw new RuntimeException("Access denied");
        }

        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        Note note;

        if (isAdmin) {
            note = noteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Error: username not found"));
        } else {
            note = noteRepository.findByIdAndUserUsername(id, username)
                    .orElseThrow(() -> new RuntimeException("Error: username not found"));
        }

        noteRepository.delete(note);
        return true;
    }


    public List<NoteResponse> getNotes() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            throw new RuntimeException("Access denied");
        }

        String username = auth.getName();
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new RuntimeException("Access denied");
        }
        List<Note> notes = noteRepository.findByUserId(user.get().getId());
        List<NoteResponse> noteResponse = new ArrayList<>();


        for (Note note : notes) {
            NoteResponse allNotes = new NoteResponse(
                    note.getId(),
                    note.getNoteTitle(),
                    note.getNoteContent(),
                    note.getCreatedAt(),
                    note.getUpdatedAt()
            );
            noteResponse.add(allNotes);
        }

        return noteResponse;
    }
    public boolean addNote(NoteRequest noteRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new RuntimeException("Access denied");
        }
         String username = auth.getName();

        if (userRepository.findByUsername(username).isEmpty()) {
            return false;
        }

        Note note = new Note();
        note.setNoteTitle(noteRequest.getNoteTitle());
        note.setNoteContent(noteRequest.getNoteContent());
        note.setUser(userRepository.findByUsername(username).get());

        noteRepository.save(note);

        return true;
    }
}


