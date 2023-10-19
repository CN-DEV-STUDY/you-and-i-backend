package com.i.and.you.auth.dto;

import jakarta.validation.constraints.Email;

public record LoginRequest(

        @Email
        String email,
        String password
) {
}
