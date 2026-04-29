package se.twowall.notes.dto;

public record LoginResponse(
        String token,
        String type,
        String username,
        String roles) {
}
