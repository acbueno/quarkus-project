package br.com.acbueno.quarkus.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.acbueno.quarkus.entity.Brand;
import br.com.acbueno.quarkus.exception.ServiceException;
import br.com.acbueno.quarkus.repository.BrandRepository;
import br.com.acbueno.quarkus.vo.BrandRequestVO;
import br.com.acbueno.quarkus.vo.BrandResponseVO;

@ApplicationScoped
public class BrandService {

    @Inject
    BrandRepository brandRepository;

    public BrandResponseVO getBrandName(String brandName) {
        try {
            return BrandResponseVO.create(brandRepository.findByBrandName(brandName));
        } catch (Exception e) {
            throw new ServiceException("No Brand found for brandGetName[%s]", brandName);
        }

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
            brandResponseVO = BrandResponseVO.create(findById);
        } else {
            throw new ServiceException("No Brand found for brandId[%s]");
        }
        return brandResponseVO;
    }

    @Transactional
    public void delete(String brandName) {
        Brand findByBrandName = brandRepository.findByBrandName(brandName);
        if(findByBrandName != null) {
            brandRepository.deleteByBrandName(brandName);
        } else {
            throw new ServiceException("No Brand found for brandId[%s]", brandName);
        }
    }

}
