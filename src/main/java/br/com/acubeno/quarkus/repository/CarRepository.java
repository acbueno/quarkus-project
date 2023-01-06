package br.com.acubeno.quarkus.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.com.acubeno.quarkus.entity.Brand;
import br.com.acubeno.quarkus.entity.Car;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {

    public Car findByCarName(String carName) {
        return find("name", carName).firstResult();
    }

    public Car findByCarModel(String model) {
        return find("model", model).firstResult();
    }

    public List<Car> listCarByYearFactory(int yearFactory){
        return list("yearFactory", yearFactory);
    }

    public List<Car> listCarByYearModel(int yearModel) {
        return list("yearModel", yearModel);
    }

    public List<Car> listCarByColor(String color){
        return list("color", color);
    }

    public Car findByChasssisNumber(String chassisNumber) {
        return find("chassisNumber", chassisNumber).firstResult();
    }

    public List<Car> listCarByBrand(Brand brand){
       return list("brand", brand);
    }

}
