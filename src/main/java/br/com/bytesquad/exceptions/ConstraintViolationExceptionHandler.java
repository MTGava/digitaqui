package br.com.bytesquad.exceptions;

import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        String violations = exception.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.joining(", "));

        return Response.status(Response.Status.BAD_REQUEST).entity(ErrorResponse.builder()
            .message(violations)
            .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
            .timestamp(Instant.now().toString())
            .traceId(UUID.randomUUID().toString())
            .build())
        .build();
    }
}
