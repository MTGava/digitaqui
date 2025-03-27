package br.com.bytesquad.exceptions;

import java.time.Instant;
import java.util.UUID;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof BadRequestException) {
            return buildResponse(exception.getMessage(), Response.Status.BAD_REQUEST);
        } else if (exception instanceof NotFoundException) {
            return buildResponse(exception.getMessage(), Response.Status.NOT_FOUND);
        } else {
            return buildResponse(exception.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

     private Response buildResponse(String message, Response.Status status) {
        return Response.status(status).entity(ErrorResponse.builder()
            .message(message)
            .statusCode(status.getStatusCode())
            .timestamp(Instant.now().toString())
            .traceId(UUID.randomUUID().toString())
            .build())
        .build();
    }
}
