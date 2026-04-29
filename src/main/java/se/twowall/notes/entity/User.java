package se.twowall.notes.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password_hash;

    @Column(name = "user_role")
    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Note> notes;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password_hash = password;
    }

    public Long getId() {return id;}
    public String getUsername() {return username;}
    public String getPassword() {return password_hash;}
    public String getRole() {return role;}
    public List<Note> getNotes() {return notes;}

    public void setId(Long id) {this.id = id;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password_hash = password;}
    public void setRole(String role) {this.role = role;}
    public void setNotes(List<Note> notes) {this.notes = notes;}
}
