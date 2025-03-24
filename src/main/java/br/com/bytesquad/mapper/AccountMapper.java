package br.com.bytesquad.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.bytesquad.domain.Account;
import br.com.bytesquad.domain.dto.AccountDTO;

@Mapper(componentModel = "cdi")
public interface AccountMapper {
    
    @Mapping(target = "id", expression = "java(a.id.toHexString())")
    AccountDTO toDTO(Account a);

    @Mapping(target = "id", expression = "java(a.id() != null ? new org.bson.types.ObjectId(a.id()) : null)")
    Account toEntity(AccountDTO a);
}
