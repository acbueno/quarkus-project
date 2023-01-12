package br.com.acbueno.quarkus.service;

import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.com.acbueno.quarkus.exception.ServiceException;
import br.com.acbueno.quarkus.service.BrandService;
import br.com.acbueno.quarkus.vo.BrandRequestVO;
import br.com.acbueno.quarkus.vo.BrandResponseVO;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class BrandServiceTest {

    @Inject
    BrandService brandService;

    @Test
    @Order(1)
    public void test_GetBrandThenFindValue() {
        BrandResponseVO brandName = brandService.getBrandName("NISSAN");
        Assertions.assertEquals("NISSAN", brandName.getBrandName());
    }

    @Test
    @Order(2)
    public void test_ListBrand() {
        List<BrandResponseVO> listAllBrands = brandService.listAllBrands();
        Assertions.assertNotNull(listAllBrands);
    }

    @Test
    @Order(3)
    public void test_getBrandThenFail() {
        Assertions.assertThrows(ServiceException.class, () ->  brandService.getBrandName("Hyundai"));
    }

    @Test
    @Order(4)
    public void test_updateBrand() {
        Long id = 1L;
        BrandRequestVO brandRequestVO = new BrandRequestVO();
        brandRequestVO.setBrandName("Citroen");
        BrandResponseVO updateValueFromDb = brandService.update(id, brandRequestVO);
        Assertions.assertEquals("Citroen", updateValueFromDb.getBrandName());
    }

    @Test
    @Order(5)
    public void test_updateFail() {
        Long id = 0L;
        BrandRequestVO brandRequestVO = new BrandRequestVO();
        brandRequestVO.setBrandName("Cherry");
        try {
            brandService.update(id, brandRequestVO);
        } catch (Exception e) {
            Assertions.assertThrows(ServiceException.class, () ->   brandService.update(id, brandRequestVO));
        }
    }

    @Test
    @Order(6)
    public void test_saveBrand() {
        BrandRequestVO brandRequestVO = new BrandRequestVO();
        brandRequestVO.setBrandName("Honda");
        BrandResponseVO saveBrandValue = brandService.saveBrand(brandRequestVO);
        Assertions.assertEquals("Honda", saveBrandValue.getBrandName());
    }

    @Test()
    @Order(7)
    public void test_deleteBrand() {
        BrandRequestVO brandRequestVO = new BrandRequestVO();
        brandRequestVO.setBrandName("Hyundai");
        brandService.saveBrand(brandRequestVO);
        brandService.delete("Hyundai");
        Assertions.assertThrows(ServiceException.class, () ->  brandService.getBrandName("Hyundai"));
    }

    @Test
    @Order(8)
    public void test_deleteFailNotFoundCar() {
        try {
            brandService.delete("teste");
        } catch (Exception e) {
            Assertions.assertThrows(ServiceException.class, () ->  brandService.getBrandName("Hyundai"));
        }
    }

}
