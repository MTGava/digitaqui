package br.com.bytesquad.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountDTO(
        String id,

        @NotBlank(message = "Name cannot be blank")
        @Size(max = 50, message = "Name must not exceed 50 characters")
        String name,

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        @Size(max = 100, message = "Email must not exceed 100 characters")
        String email,

        @NotBlank(message = "Password cannot be blank")
        @Size(max = 20, message = "Password must not exceed 20 characters")
        String password) {

                public String getId() {
                        return id;
                }
        };