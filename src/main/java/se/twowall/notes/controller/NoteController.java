package se.twowall.notes.controller;

import org.springframework.web.bind.annotation.*;
import se.twowall.notes.dto.NoteRequest;
import se.twowall.notes.dto.NoteResponse;
import se.twowall.notes.service.AdminService;
import se.twowall.notes.service.NoteService;


import java.util.List;

@RestController
@RequestMapping("/api")

public class NoteController {
    private final NoteService noteService;
    private final AdminService adminService;

    public NoteController(NoteService noteService, AdminService adminService) {
        this.noteService = noteService;
        this.adminService = adminService;
    }
    @GetMapping("/notes/")
    public List<NoteResponse> getNotes() {
        return noteService.getNotes();
    }

    @GetMapping("/notes/{id}")
    public NoteResponse getNote(@PathVariable Long id) {
        return noteService.getNoteByID(id);
    }

    @DeleteMapping("/notes/{id}")
    public boolean deleteNote(@PathVariable Long id) {
        return noteService.deleteNoteByID(id);
    }

    @PatchMapping("/notes/{id}")
    public boolean updateNote(@PathVariable Long id,
                              @RequestBody NoteRequest noteRequest) {

        return noteService.updateNoteByID(id, noteRequest);
    }

    @PutMapping("notes/add")
    public boolean addNote(@RequestBody NoteRequest noteRequest) {
        return noteService.addNote(noteRequest);
    }

    //ADMIN (Move to AdminController?) ---------
    @GetMapping("/admin/users/notes/")
    public List<NoteResponse> fetchAllNotes() {
        return adminService.fetchAllNotes();
    }
}