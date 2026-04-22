package se.twowall.notes.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_]+$")
    @Size(min = 3, max = 25)
    private String username;

    @NotBlank
    @Size(min = 12, max = 100)
    private String password;


    public String getUsername() {return username;}
    public String getPassword() {return password;}

    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
}
