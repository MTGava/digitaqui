package br.com.bytesquad.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record InputsDTO(
    String id,

    @NotBlank(message = "Inputs cannot be blank")
    @Pattern(
        regexp = "^([^;]{3,};[^;]{3,})(;[^;]{3,})*$", 
        message = "Inputs must contain at least two values separated by ';', and each value must have at least 3 characters"
    )
    String inputs,
    String accountId,

    @NotBlank(message = "Active cannot be blank")
    @Pattern(
        regexp = "^(true|false)$",
        message = "Active must be either 'true' or 'false'"
    )
    String active
) {};
