package se.twowall.notes.dto;

public class NoteRequest {
    private Long userId;
    private String noteTitle;
    private String noteContent;

    public Long getUserId() {return userId;}
    public String getNoteTitle() {return noteTitle;}
    public String getNoteContent() {return noteContent;}

    public void setUserId(Long userId) {this.userId = userId;}
    public void setNoteTitle(String noteTitle) {this.noteTitle = noteTitle;}
    public void setNoteContent(String noteContent) {this.noteContent = noteContent;}
}
