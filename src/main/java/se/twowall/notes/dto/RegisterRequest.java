package se.twowall.notes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegisterRequest {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_]+$")
    private String username;

    @NotBlank
    private String password;


    public String getUsername() {return username;}
    public String getPassword() {return password;}

    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
}
