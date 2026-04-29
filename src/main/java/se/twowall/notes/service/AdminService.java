package se.twowall.notes.service;

import org.springframework.stereotype.Service;
import se.twowall.notes.dto.NoteResponse;
import se.twowall.notes.dto.UserResponse;
import se.twowall.notes.entity.Note;
import se.twowall.notes.entity.User;
import se.twowall.notes.repository.NoteRepository;
import se.twowall.notes.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    NoteRepository noteRepository;
    UserRepository userRepository;

    public AdminService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public List<NoteResponse> fetchAllNotes() {
        List<Note> notes = noteRepository.findAll();
        if (notes.isEmpty()) {
            System.out.println("Error: fetchAllNotes()"); //TODO: Add proper throw.
        }

        List<NoteResponse> noteResponses = new ArrayList<>();
        for (Note note : notes) {
            NoteResponse allNotes = new NoteResponse(
                    note.getId(),
                    note.getNoteTitle(),
                    note.getNoteContent(),
                    note.getCreatedAt(),
                    note.getUpdatedAt()
            );
            noteResponses.add(allNotes);
        }
        return noteResponses;
    }

//    public List<UserResponse> fetchAllUsers() {
//        List<User> users = userRepository.findAll();
//        if (users.isEmpty()) {
//            System.out.println("Error: fetchAllNotes()"); //TODO: Add proper throw.
//        }
//
//        List<UserResponse> userResponses = new ArrayList<>();
//        for (User userResponse : user) {
//            NoteResponse allNotes = new NoteResponse(
//                    user.getId(),
//                    user.getNoteTitle(),
//                    user.getNoteContent(),
//                    user.getCreatedAt(),
//                    user.getUpdatedAt()
//            );
//            noteResponses.add(allNotes);
//        }
//        return noteResponses;
//    }
}
