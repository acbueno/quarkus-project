package br.com.acubeno.quarkus.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.rowset.serial.SerialException;
import javax.transaction.Transactional;

import br.com.acubeno.quarkus.entity.Brand;
import br.com.acubeno.quarkus.exception.ServiceException;
import br.com.acubeno.quarkus.repository.BrandRepository;
import br.com.acubeno.quarkus.vo.BrandRequestVO;
import br.com.acubeno.quarkus.vo.BrandResponseVO;
import br.com.acubeno.quarkus.vo.EngineRequestVO;

@ApplicationScoped
public class BrandService {

    @Inject
    BrandRepository brandRepository;

    public BrandResponseVO getBrandName(String brandNane) {
        return BrandResponseVO.create(brandRepository.findByBrandName(brandNane));
    }

    @Transactional
    public BrandResponseVO saveBrand(BrandRequestVO brandRequestVO) {
        brandRepository.persist(Brand.create(brandRequestVO));
        BrandResponseVO brandResponse = getBrandName(brandRequestVO.getBrandName());

        return brandResponse;
    }

    public List<BrandResponseVO> listAllBrands(){
        List<BrandResponseVO> listBrandVO = new ArrayList();
        List<Brand> listBrandEntity = brandRepository.listAll();

        for (Brand brand : listBrandEntity) {
             listBrandVO.add(BrandResponseVO.create(brand));
        }
        return listBrandVO;
    }

    public BrandResponseVO update(Long id,  BrandRequestVO brandRequestVO) {
        Brand findById = brandRepository.findById(id);
        BrandResponseVO brandResponseVO;

        if(findById != null) {
            brandResponseVO = new BrandResponseVO();
            findById.setBrandName(brandRequestVO.getBrandName());
            brandRepository.persist(findById);
            BrandResponseVO.create(findById);
        } else {
            throw new ServiceException("No Brand found for brandId[%s]");
        }
        return brandResponseVO;
    }

    public void delete(String brandName) {
        Brand findByBrandName = brandRepository.findByBrandName(brandName);
        if(findByBrandName != null) {
            brandRepository.delete(findByBrandName);
        } else {
            throw new ServiceException("No Brand found for brandId[%s]");
        }
    }

}
