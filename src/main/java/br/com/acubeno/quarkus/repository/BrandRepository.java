package br.com.acubeno.quarkus.repository;

import javax.enterprise.context.ApplicationScoped;
import br.com.acubeno.quarkus.entity.Brand;
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
