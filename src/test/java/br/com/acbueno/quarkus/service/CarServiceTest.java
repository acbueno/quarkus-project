package br.com.acbueno.quarkus.service;

import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.com.acbueno.quarkus.exception.ServiceException;
import br.com.acbueno.quarkus.service.CarSevice;
import br.com.acbueno.quarkus.vo.CarRequestVO;
import br.com.acbueno.quarkus.vo.CarResponseVO;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CarServiceTest {

    @Inject
    CarSevice carSevice;

    @Test
    @Order(1)
    public void test_getCarByName() {
        String carName = "Versa";
        carSevice.getCarByName(carName);
        Assertions.assertNotNull(carName);
    }

    @Test
    @Order(2)
    public void test_getCarByModel() {
        String carModel = "SV";
        CarResponseVO carByModel = carSevice.getCarByModel(carModel);
        Assertions.assertNotNull(carByModel);
    }

    @Test
    @Order(3)
    public void test_listCarByYearFactory() {
        int yearFactory = 2016;
        List<CarResponseVO> listCarByYearFactory = carSevice.listCarByYearFactory(yearFactory);
        Assertions.assertNotNull(listCarByYearFactory);
    }

    @Test
    @Order(4)
    public void test_listCarByYearModel() {
        int yearModel = 2017;
        List<CarResponseVO> listCarByYearModel = carSevice.listCarByYearModel(yearModel);
        Assertions.assertNotNull(listCarByYearModel);
    }

    @Test
    @Order(5)
    public void test_listCarByColor() {
        String color = "Cinza Magnum";
        List<CarResponseVO> listCarByColor = carSevice.listCarByColor(color);
        Assertions.assertNotNull(listCarByColor);
    }

    @Test
    @Order(6)
    public void test_findCarByChassis() {
        String chassis = "9JYZ-6C9T-4HV8-4XJP";
        CarResponseVO findCarByChassis = carSevice.findCarByChassis(chassis);
        Assertions.assertNotNull(findCarByChassis);
    }

    @Test
    @Order(7)
    public void test_listCarByBrand() {
        String brandName = "NISSAN";
        List<CarResponseVO> listCarByBrand = carSevice.listCarByBrand(brandName);
        Assertions.assertNotNull(listCarByBrand);
    }

    @Test
    @Order(8)
    public void test_saveCar() {
        CarRequestVO carRequestVO = new CarRequestVO();
        carRequestVO.setBrandName("TOYOTA");
        carRequestVO.setChassisNumber("ZWO2-VPY6-UTLB-U9IS");
        carRequestVO.setColor("Prata Madri");
        carRequestVO.setEngineCode("BN-DG");
        carRequestVO.setName("Corrola");
        carRequestVO.setYearFactory(2010);
        carRequestVO.setYearModel(2011);
        CarResponseVO db = carSevice.save(carRequestVO);
        Assertions.assertNotNull(db);
    }

    @Test
    @Order(9)
    public void test_updateSuccess() {
        String modelName = "GOL";
        CarRequestVO carRequestVO = new CarRequestVO();
        carRequestVO.setBrandName("VW");
        carRequestVO.setChassisNumber("9JYZ-6C9T-4HV8-4XJP");
        carRequestVO.setColor("Verde Copa");
        carRequestVO.setModel("Confortline");
        carRequestVO.setYearFactory(2019);
        carRequestVO.setYearModel(2019);
        carRequestVO.setName("GOL");
        carRequestVO.setEngineCode("Duratec");
        CarResponseVO updateDB = carSevice.update(modelName, carRequestVO);
        Assertions.assertNotNull(updateDB);
    }

    @Test
    @Order(10)
    public void test_updateFail() {
        String nameCar = "Civic";
        CarRequestVO carRequestVO = new CarRequestVO();
        carRequestVO.setBrandName("HONDA");
        carRequestVO.setChassisNumber("KH9N-GAJR-FE6W-FU3D");
        carRequestVO.setColor("Prata Madri");
        carRequestVO.setModel("EXL");
        carRequestVO.setYearFactory(2010);
        carRequestVO.setYearModel(2011);
        carRequestVO.setName("Civic");
        try {
            carSevice.update(nameCar, carRequestVO);
        } catch (Exception e) {
            Assertions.assertThrows(ServiceException.class, () -> carSevice.update(nameCar, carRequestVO));
        }
    }

    @Test
    @Order(11)
    public void test_deleteByModel() {
        CarRequestVO carRequestVO = new CarRequestVO();
        carRequestVO.setBrandName("VW");
        carRequestVO.setChassisNumber("325V-QVGP-O024-YGQL");
        carRequestVO.setColor("Cinza Ubatuba");
        carRequestVO.setModel("Highline");
        carRequestVO.setYearFactory(2022);
        carRequestVO.setYearModel(2022);
        carRequestVO.setName("Virtus");
        carRequestVO.setEngineCode("BN-DG");
        carSevice.save(carRequestVO);
        carSevice.deleteByModel("Highline");
        Assertions.assertThrows(ServiceException.class, () -> carSevice.getCarByModel("Highline"));
    }

    @Test
    @Order(12)
    public void test_deleteByName() {
        CarRequestVO carRequestVO = new CarRequestVO();
        carRequestVO.setBrandName("VW");
        carRequestVO.setChassisNumber("AXZD-X0AH-V5WM-5LK3");
        carRequestVO.setColor("Cinza Ubatuba");
        carRequestVO.setModel("Highline");
        carRequestVO.setYearFactory(2022);
        carRequestVO.setYearModel(2022);
        carRequestVO.setName("Nivus");
        carRequestVO.setEngineCode("BN-DG");
        carSevice.save(carRequestVO);
        carSevice.deleteByName("Nivus");
        Assertions.assertThrows(ServiceException.class, () -> carSevice.getCarByName("Nivus"));
    }

    @Test
    @Order(13)
    public void test_deleteByModelFail() {
        String model = "Basico";
        try {
            carSevice.deleteByModel(model);
        } catch (Exception e) {
            Assertions.assertThrows(ServiceException.class, () -> carSevice.getCarByModel(model));
        }
    }

    @Test
    @Order(14)
    public void test_deleteByNameCarFail() {
        String nameCar = "BR-800";
        try {
            carSevice.deleteByName(nameCar);
        } catch (Exception e) {
            Assertions.assertThrows(ServiceException.class, () -> carSevice.getCarByName(nameCar));
        }
    }

    @Test
    @Order(15)
    public void test_ListAllCar() {
        List<CarResponseVO> listAllCars = carSevice.listAllCars();
        Assertions.assertNotNull(listAllCars);
    }
}
