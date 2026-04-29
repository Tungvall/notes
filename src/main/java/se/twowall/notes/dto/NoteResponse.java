package se.twowall.notes.dto;

import java.util.Date;

public record NoteResponse(Long id, String noteTitle, String noteContent, Date createdAt, Date updatedAt) {}
