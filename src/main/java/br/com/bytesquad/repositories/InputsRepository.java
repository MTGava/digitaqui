package br.com.bytesquad.repositories;

import br.com.bytesquad.domain.Inputs;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InputsRepository implements PanacheMongoRepository<Inputs> {

}
