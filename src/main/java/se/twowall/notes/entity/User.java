package se.twowall.notes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password_hash;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password_hash = password;
    }

    public Long getId() {return id;}
    public String getUsername() {return username;}
    public String getPassword() {return password_hash;}

    public void setId(Long id) {this.id = id;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password_hash = password;}
}
