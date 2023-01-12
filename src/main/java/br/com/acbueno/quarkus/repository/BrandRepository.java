package br.com.acbueno.quarkus.repository;

import javax.enterprise.context.ApplicationScoped;

import br.com.acbueno.quarkus.entity.Brand;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class BrandRepository implements PanacheRepository<Brand> {

    public Brand findByBrandName(String brandName) {
        return find("brandName", brandName).firstResult();
    }

    public void deleteByBrandName(String brandName) {
        delete("brandName", brandName);
    }

}
