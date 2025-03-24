package br.com.bytesquad.services;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.bson.types.ObjectId;

import br.com.bytesquad.domain.dto.AccountDTO;
import br.com.bytesquad.mapper.AccountMapper;
import br.com.bytesquad.repositories.AccountRepository;
import io.netty.util.internal.ObjectUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class AccountService {

    @Inject
    private AccountMapper mapper;

    @Inject
    private AccountRepository repository;

    
    public List<AccountDTO> getAllAccounts() {
        return repository.listAll().stream().map(mapper::toDTO).toList();
    }

    public AccountDTO getAccountById(String id) {
        return mapper.toDTO(repository.findById(new ObjectId(id)));
    }

    public AccountDTO addAccount(AccountDTO accountDTO) {
        if(hasEmail(accountDTO.email())) {
            throw new IllegalArgumentException("Email already exists");
        }
        var account = mapper.toEntity(accountDTO);
        account.id = null;
        repository.persist(account);
        return mapper.toDTO(account);
    }

    public void deleteAccount(String id) {
        repository.deleteById(new ObjectId(id));
    }

    public boolean hasEmail(String email) {
        var account = repository.find("email", email).firstResult();
        if (account == null) {
            return false;
        }
        return true;
    }
}
