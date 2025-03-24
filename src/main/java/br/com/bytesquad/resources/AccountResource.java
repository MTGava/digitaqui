package br.com.bytesquad.resources;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import br.com.bytesquad.domain.Account;
import br.com.bytesquad.domain.dto.AccountDTO;
import br.com.bytesquad.services.AccountService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Path("/accounts")
@Slf4j
public class AccountResource {

    @Inject
    private AccountService accountService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response allAccounts() {
        return Response.ok(accountService.getAllAccounts()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountById(String id) {
        return Response.ok(accountService.getAccountById(id)).build();
    }

    @POST
    public Response addAccount(AccountDTO dto) {
        var account = accountService.addAccount(dto);
        return Response
                .created(URI.create("/accounts/" + account.id()))
                .entity(account)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAccount(String id) {
        accountService.deleteAccount(id);
        return Response.noContent().build();
    }
}
