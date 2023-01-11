package br.com.acubeno.quarkus.service;

import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import br.com.acubeno.quarkus.exception.ServiceException;
import br.com.acubeno.quarkus.vo.BrandRequestVO;
import br.com.acubeno.quarkus.vo.BrandResponseVO;
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
        Assertions.assertEquals(5, listAllBrands.size());
    }

    @Test
    @Order(3)
    public void test_getBrandThenFail() {
        Assertions.assertThrows(ServiceException.class, () ->  brandService.getBrandName("Hyundai"));
    }

    @Test
    @Order(4)
    public void test_UpdateBrand() {
        Long id = 1L;
        BrandRequestVO brandRequestVO = new BrandRequestVO();
        brandRequestVO.setBrandName("Citroen");
        BrandResponseVO updateValueFromDb = brandService.update(id, brandRequestVO);
        Assertions.assertEquals("Citroen", updateValueFromDb.getBrandName());
    }

    @Test
    @Order(5)
    public void test_SaveBrand() {
        BrandRequestVO brandRequestVO = new BrandRequestVO();
        brandRequestVO.setBrandName("Honda");
        BrandResponseVO saveBrandValue = brandService.saveBrand(brandRequestVO);
        Assertions.assertEquals("Honda", saveBrandValue.getBrandName());
    }

    @Test()
    @Order(6)
    public void test_DeleteBrand() {
        BrandRequestVO brandRequestVO = new BrandRequestVO();
        brandRequestVO.setBrandName("Hyundai");
        brandService.saveBrand(brandRequestVO);
        brandService.delete("Hyundai");
        Assertions.assertThrows(ServiceException.class, () ->  brandService.getBrandName("Hyundai"));
    }

}
