package br.com.bytesquad.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.bytesquad.domain.Inputs;
import br.com.bytesquad.domain.dto.InputsDTO;

@Mapper(componentModel = "cdi")
public interface InputMapper {
    
    @Mapping(target = "id", expression = "java(a.id.toHexString())")
    @Mapping(target = "accountId", expression = "java(a.accountId.toHexString())")
    public InputsDTO toDTO(Inputs a);

    @Mapping(target = "id", expression = "java(a.id() != null ? new org.bson.types.ObjectId(a.id()) : null)")
    @Mapping(target = "accountId", expression = "java(a.accountId() != null ? new org.bson.types.ObjectId(a.accountId()) : null)")
    public Inputs toEntity(InputsDTO a);
}
