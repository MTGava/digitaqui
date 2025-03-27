package br.com.bytesquad.services;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import br.com.bytesquad.domain.Inputs;
import br.com.bytesquad.domain.dto.InputsDTO;
import br.com.bytesquad.mapper.InputMapper;
import br.com.bytesquad.repositories.InputsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class InputsService {

    @Inject 
    private AccountService accountService;

    @Inject
    private InputsRepository repository;

    @Inject
    private InputMapper mapper;

    public InputsDTO addInput(InputsDTO inputsDTO) {
        accountService.verifyAccount(inputsDTO.accountId());
        
        var inputs = mapper.toEntity(inputsDTO);
        inputs.id = null;

        if (Boolean.valueOf(inputs.active)) {
            // Atualiza todos os inputs associados ao accountId para active = false
            repository.update("active = false").where("accountId", new ObjectId(inputsDTO.accountId()));
        }

        repository.persist(inputs);

        return mapper.toDTO(inputs);
    }

    public InputsDTO getInputsById(String id) {
        verifyInputs(id);
        var inputs = repository.findById(new ObjectId(id));

        return mapper.toDTO(inputs);
    }

    public void verifyInputs(String inputsId) {
        var inputs = repository.findById(new ObjectId(inputsId));
        if (inputs == null) {
            throw new NotFoundException("Inputs not found");
        }
    }

    public List<InputsDTO> getInputsByAccountId(String accountId) {
        accountService.verifyAccount(accountId);
        
        var inputs = repository.find("accountId", new ObjectId(accountId));
        return inputs.list().stream().map(mapper::toDTO).toList();
    }

	public void deleteInput(String id) {
        verifyInputs(id);
        repository.deleteById(new ObjectId(id));
	}

    public void deleteAllInputsByAccountId(String accountId) {
        accountService.verifyAccount(accountId);
        repository.delete("accountId", new ObjectId(accountId));
    }
}
