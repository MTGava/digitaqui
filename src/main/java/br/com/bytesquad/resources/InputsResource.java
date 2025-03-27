package br.com.bytesquad.resources;

import java.net.URI;

import br.com.bytesquad.domain.dto.InputsDTO;
import br.com.bytesquad.services.InputsService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Path("/inputs")
@Slf4j
public class InputsResource {

    @Inject
    private InputsService inputsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getInputsById(String id) {
        return Response.ok(inputsService.getInputsById(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/account/{accountId}")
    public Response getInputsByAccountId(String accountId) {
        return Response.ok(inputsService.getInputsByAccountId(accountId)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addInput(@Valid InputsDTO inputsDTO) {
        var input = inputsService.addInput(inputsDTO);
        return Response
                .created(URI.create("/inputs/" + input.id()))
                .entity(input)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteInput(String id) {
        inputsService.deleteInput(id);
        return Response.noContent().build();
    }
    
}
