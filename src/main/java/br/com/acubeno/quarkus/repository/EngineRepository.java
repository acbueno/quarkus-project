package br.com.acubeno.quarkus.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.com.acubeno.quarkus.entity.Engine;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class EngineRepository implements PanacheRepository<Engine> {

    public List<Engine> listByCapacity(Double capacity){
       return list("capacity", capacity);
    }

    public Engine listByCodeNumber(String codeNumber) {
        return find("code", codeNumber).firstResult();
    }

    public List<Engine> listByHp(int hp) {
        return list("hp", hp);
    }

}
