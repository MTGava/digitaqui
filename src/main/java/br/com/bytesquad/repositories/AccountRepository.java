package br.com.bytesquad.repositories;

import br.com.bytesquad.domain.Account;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountRepository implements PanacheMongoRepository<Account> {

}
