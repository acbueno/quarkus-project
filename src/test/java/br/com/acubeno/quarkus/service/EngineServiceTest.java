package br.com.acubeno.quarkus.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import br.com.acubeno.quarkus.exception.ServiceException;
import br.com.acubeno.quarkus.vo.EngineRequestVO;
import br.com.acubeno.quarkus.vo.EngineResponseVO;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class EngineServiceTest {

    @Inject
    EngineService engineService;

    @Test
    @Order(1)
    public void test_getEngineByCodeSuccess() {
        String code = "MR-18";
        EngineResponseVO engineByCodeNumber = engineService.getEngineByCodeNumber(code);
        Assertions.assertNotNull(engineByCodeNumber);
    }

    @Test
    @Order(2)
    public void test_getEngineCodeFail() {
        Assertions.assertThrows(ServiceException.class, () ->   engineService.getEngineByCodeNumber("000"));
    }

    @Test
    @Order(3)
    public void test_getEngineByCapacitySuccess() {
        Double capacity = 1.6;
        List<EngineResponseVO> egineByCapacity = engineService.getEgineByCapacity(capacity);
        Assertions.assertNotNull(egineByCapacity);
    }

    @Test
    @Order(4)
    public void test_gentEngineByCapacityNotFound() {
        Double capacity = 8.2;
        List<EngineResponseVO> engineByCapacity = engineService.getEgineByCapacity(capacity);
        Assertions.assertEquals(0, engineByCapacity.size());
    }

    @Test
    @Order(5)
    public void test_getListEngineByHp() {
        int hp = 111;
        List<EngineResponseVO> listEngineByHp = engineService.getListEngineByHp(hp);
        Assertions.assertNotNull(listEngineByHp);
    }

    @Test
    @Order(6)
    public void test_getListEngineByHpNotFound() {
        int hp = 800;
        List<EngineResponseVO> listEngineByHp = engineService.getListEngineByHp(hp);
        Assertions.assertEquals(0, listEngineByHp.size());
    }

    @Test
    @Order(7)
    public void test_saveEngineSucess() {
        EngineRequestVO engineRequestVO = new EngineRequestVO();
        engineRequestVO.setCode("96-YC");
        engineRequestVO.setCapacity(2.2);
        engineRequestVO.setHp(178);
        EngineResponseVO save = engineService.save(engineRequestVO);
        Assertions.assertNotNull(save);
    }

    @Test
    @Order(8)
    public void test_updateEngineSuccess() {
        Long code = 1L;
        EngineRequestVO engineRequestVO = new EngineRequestVO();
        engineRequestVO.setCode("Q5-EO");
        engineRequestVO.setCapacity(1.0);
        engineRequestVO.setHp(70);
        engineService.update(code, engineRequestVO);
        Assertions.assertNotNull(engineRequestVO);
    }

    @Test
    @Order(9)
    public void test_updateFail() {
        Long code = 40L;
        EngineRequestVO engineRequestVO = new EngineRequestVO();
        Assertions.assertThrows(ServiceException.class, () ->   engineService.update(code, engineRequestVO));
    }

    @Test
    @Order(10)
    public void test_listAllEngine() {
        List<EngineResponseVO> listAllEngine = engineService.listAllEngine();
        assertNotNull(listAllEngine);
    }

    @Test
    @Order(11)
    public void  test_deleteEngineSuccess() {
        EngineRequestVO engineRequestVO = new EngineRequestVO();
        engineRequestVO.setCode("98-YC");
        engineRequestVO.setCapacity(2.3);
        engineRequestVO.setHp(185);
        EngineResponseVO save = engineService.save(engineRequestVO);
        String code = save.getCode();
        engineService.delete(code);
        Assertions.assertNotNull(code);
    }

    @Test
    @Order(12)
    public void test_deleteEngineReferenceEntiryCarFailed() {
        String code = "EA-111";
        Assertions.assertThrows(ServiceException.class, () ->   engineService.delete(code));
    }

    @Test
    @Order(13)
    public void test_deleteEngineCodeNotFound() {
        String code = "EA-88";
        Assertions.assertThrows(NullPointerException.class, () ->   engineService.delete(code));
    }

}
