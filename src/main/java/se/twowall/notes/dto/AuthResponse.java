package se.twowall.notes.dto;

public class AuthResponse {

    private final String username;
    private final String token;

    public AuthResponse(Long id, String username, String token) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {return token;}
    public String getUsername() {return username;}
}
