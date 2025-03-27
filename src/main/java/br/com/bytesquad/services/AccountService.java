package br.com.bytesquad.services;

import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;

import br.com.bytesquad.domain.dto.AccountDTO;
import br.com.bytesquad.mapper.AccountMapper;
import br.com.bytesquad.repositories.AccountRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class AccountService {

    @Inject
    private AccountMapper mapper;

    @Inject
    private AccountRepository repository;

    @Inject
    private InputsService inputsService;

    
    public List<AccountDTO> getAllAccounts() {
        return repository.listAll().stream().map(mapper::toDTO).toList();
    }

    public AccountDTO getAccountById(String id) {
        verifyAccount(id);
        var account = repository.findById(new ObjectId(id));
        return mapper.toDTO(account);
    }

    public AccountDTO addAccount(AccountDTO accountDTO) {
        verifyEmail(accountDTO.email());
        var account = mapper.toEntity(accountDTO);
        account.id = null;
        repository.persist(account);
        return mapper.toDTO(account);
    }

    public void deleteAccount(String id) {
        verifyAccount(id);
        inputsService.deleteAllInputsByAccountId(id);
        repository.deleteById(new ObjectId(id));
    }

    public void verifyEmail(String email) {
        var account = repository.find("email", email).firstResult();
        if (Objects.nonNull(account)) {
            throw new BadRequestException("Email already exists");
        }
    }

    public void verifyAccount(String accountId) {
        var account = repository.findById(new ObjectId(accountId));
        if (Objects.isNull(account)) {
            throw new NotFoundException("Account not found");
        }
    }
}
