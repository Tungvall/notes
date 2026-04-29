package se.twowall.notes.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "notes")

public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "note_title", length = 50)
    private String noteTitle;

    @Column(name = "note_content", length = 255)
    private String noteContent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Note() {}

    public Note(long userId, String noteTitle, String noteContent, User user) {
        this.user = user;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public long getId() {return id;}
    public User getUser() {return user;}
    public String getNoteTitle() {return noteTitle;}
    public String getNoteContent() {return noteContent;}
    public Date getCreatedAt() {return createdAt;}
    public Date getUpdatedAt() {return updatedAt;}

    public void setId(long id) {this.id = id;}
    public void setUser(User user) {this.user = user;}
    public void setNoteTitle(String noteTitle) {this.noteTitle = noteTitle;}
    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
    public void setCreatedAt(Date createdAt) {this.createdAt = createdAt;}

}
