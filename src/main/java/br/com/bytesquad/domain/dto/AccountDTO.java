package br.com.bytesquad.domain.dto;

public record AccountDTO(
        String id,
        String name,
        String email,
        String password) {
};
