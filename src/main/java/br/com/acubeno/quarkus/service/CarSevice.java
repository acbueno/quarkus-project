package br.com.acubeno.quarkus.service;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.jboss.logging.Logger;
import br.com.acubeno.quarkus.entity.Brand;
import br.com.acubeno.quarkus.entity.Car;
import br.com.acubeno.quarkus.entity.Engine;
import br.com.acubeno.quarkus.exception.ServiceException;
import br.com.acubeno.quarkus.repository.BrandRepository;
import br.com.acubeno.quarkus.repository.CarRepository;
import br.com.acubeno.quarkus.repository.EngineRepository;
import br.com.acubeno.quarkus.vo.CarRequestVO;
import br.com.acubeno.quarkus.vo.CarResponseVO;

@ApplicationScoped
public class CarSevice {

    @Inject
    Logger log;

    @Inject
    CarRepository carRepository;

    @Inject
    BrandRepository brandRepository;

    @Inject
    EngineRepository engineRepository;

    public CarResponseVO getCarByName(String name) {
        try {
            return CarResponseVO.create(carRepository.findByCarName(name));
        } catch (Exception e) {
            throw new ServiceException("No Car found for carGetName[%s]", name);
        }
    }

    public CarResponseVO getCarByModel(String model) {
        try {
            Car findByCarName = carRepository.findByCarModel(model);
            return CarResponseVO.create(findByCarName);
        } catch (Exception e) {
            throw new ServiceException("No Car found for carGetModel[%s]", model);
        }
    }

    public List<CarResponseVO> listCarByYearFactory(int yearFactory) {
        List<Car> listCarByYearFactory = carRepository.listCarByYearFactory(yearFactory);
        List<CarResponseVO> listCarReponse = new ArrayList<>();

        for (Car car : listCarByYearFactory) {
            listCarReponse.add(CarResponseVO.create(car));
        }

        return listCarReponse;
    }

    public List<CarResponseVO> listCarByYearModel(int yearModel) {
        List<Car> listCarByYearModel = carRepository.listCarByYearModel(yearModel);
        List<CarResponseVO> listCarResponse = new ArrayList<>();

        for (Car car : listCarByYearModel) {
            listCarResponse.add(CarResponseVO.create(car));
        }

        return listCarResponse;
    }

    public List<CarResponseVO> listCarByColor(String color) {
        List<Car> listCarByColor = carRepository.listCarByColor(color);
        List<CarResponseVO> listCarReponse = new ArrayList<>();

        for (Car car : listCarByColor) {
            listCarReponse.add(CarResponseVO.create(car));
        }

        return listCarReponse;
    }

    public CarResponseVO findCarByChassis(String chasssis) {
        return CarResponseVO.create(carRepository.findByChasssisNumber(chasssis));
    }

    public List<CarResponseVO> listCarByBrand(String brandName) {
        Brand findByBrandName = brandRepository.findByBrandName(brandName);
        List<Car> listCarByBrand = carRepository.listCarByBrand(findByBrandName);
        List<CarResponseVO> listCarReponse = new ArrayList<>();

        for (Car car : listCarByBrand) {
            listCarReponse.add(CarResponseVO.create(car));
        }

        return listCarReponse;
    }

    @Transactional
    public CarResponseVO save(CarRequestVO carRequestVO) {
        Brand findByBrandName = brandRepository.findByBrandName(carRequestVO.getBrandName());
        Engine listByCodeNumber = engineRepository.listByCodeNumber(carRequestVO.getEngineCode());

        Car carEntity = Car.create(carRequestVO);
        carEntity.getBrand().setId((findByBrandName.getId()));
        carEntity.setEngine(listByCodeNumber);
        carRepository.persistAndFlush(carEntity);
        Car findByCarName = carRepository.findByCarName(carRequestVO.getName());

        return CarResponseVO.create(findByCarName);
    }

    @Transactional
    public CarResponseVO update(String modelName, CarRequestVO carRequestVO) {
        Car findByCarName = carRepository.findByCarName(modelName);

        if (findByCarName != null) {
            findByCarName.setChassisNumber(carRequestVO.getChassisNumber());
            findByCarName.setYearFactory(carRequestVO.getYearFactory());
            findByCarName.setName(carRequestVO.getName());
            findByCarName.setModel(carRequestVO.getModel());
            findByCarName.setYearModel(carRequestVO.getYearModel());
            findByCarName.setColor(carRequestVO.getColor());
            Engine engine = engineRepository.listByCodeNumber(carRequestVO.getEngineCode());

            if (engine != null) {
                findByCarName.setEngine(engine);
            }

            Brand findByBrandName = brandRepository.findByBrandName(carRequestVO.getBrandName());

            if (findByBrandName != null) {
                findByCarName.setBrand(findByBrandName);
            }
            carRepository.persist(findByCarName);
            return CarResponseVO.create(findByCarName);
        } else {
            throw new ServiceException("No Engine found for modelName[%s]", modelName);
        }
    }

    @Transactional
    public void deleteByModel(String model) {
        try {
            Car findByCarModel = carRepository.findByCarModel(model);
            carRepository.delete(findByCarModel);
        } catch (Exception e) {
            log.errorf("Erro execute delete operation %s", e);
            throw new ServiceException("No Engine found for modelName[%s]", model);
        }
    }

    @Transactional
    public void deleteByName(String name) {
        try {
            Car findByCarName = carRepository.findByCarName(name);
            carRepository.delete(findByCarName);
        } catch (Exception e) {
            log.errorf("Erro execute delete operation %s", e);
            throw new ServiceException("No Engine found for modelName[%s]", name);
        }
    }

    public List<CarResponseVO> listAllCars() {
        List<CarResponseVO> listCarsResponseVO = new ArrayList<>();

        for(Car car:  carRepository.listAll()) {
            listCarsResponseVO.add(CarResponseVO.create(car));
        }
        return listCarsResponseVO;
    }

}
