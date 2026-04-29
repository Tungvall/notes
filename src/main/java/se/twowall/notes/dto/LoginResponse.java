package se.twowall.notes.dto;

import java.util.List;

public record LoginResponse(
        String token,
        String type,
        String username,
        String roles) {
}
