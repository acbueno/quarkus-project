package br.com.acbueno.quarkus.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.acbueno.quarkus.entity.Car;
import br.com.acbueno.quarkus.entity.Engine;
import br.com.acbueno.quarkus.exception.ServiceException;
import br.com.acbueno.quarkus.repository.CarRepository;
import br.com.acbueno.quarkus.repository.EngineRepository;
import br.com.acbueno.quarkus.vo.EngineRequestVO;
import br.com.acbueno.quarkus.vo.EngineResponseVO;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

@ApplicationScoped
public class EngineService {

    @Inject
    private EngineRepository engineRepository;

    @Inject
    private CarRepository carRepository;


    public EngineResponseVO getEngineByCodeNumber(String codeNumber) {
        try {
            return EngineResponseVO.create(engineRepository.listByCodeNumber(codeNumber));
        } catch (Exception e) {
            throw new ServiceException("No Engine found for engineCodeNumber[%s]", codeNumber);
        }
    }

    public List<EngineResponseVO> getEgineByCapacity(Double capacity) {
        List<EngineResponseVO> listEngineVO = new ArrayList<>();
        List<Engine> listByCapacity = engineRepository.listByCapacity(capacity);

        for (Engine engine : listByCapacity) {
            listEngineVO.add(EngineResponseVO.create(engine));
        }

        return listEngineVO;
    }

    public List<EngineResponseVO> getListEngineByHp(int hp){
        List<EngineResponseVO> listEngineVO = new ArrayList<>();
        List<Engine> listByHp = engineRepository.listByHp(hp);

        for (Engine engine : listByHp) {
             listEngineVO.add(EngineResponseVO.create(engine));
        }

        return listEngineVO;
    }

    @Transactional
    public EngineResponseVO save(EngineRequestVO engineVO) {
        try {
            engineRepository.persist(Engine.create(engineVO));
            EngineResponseVO engineByCodeNumber = getEngineByCodeNumber(engineVO.getCode());
            return engineByCodeNumber;
        } catch (Exception e) {
            throw new ServiceException("Error persist");
        }
    }

    @Transactional
    public EngineResponseVO update(Long id, EngineRequestVO engineVO) {
        Engine findById = engineRepository.findById(id);

        if (findById != null) {
            findById.setCode(engineVO.getCode());
            findById.setCapacity(engineVO.getCapacity());
            findById.setHp(engineVO.getHp());
            engineRepository.persist(findById);
            return getEngineByCodeNumber(engineVO.getCode());
        } else {
            throw new ServiceException("No Engine found for engineId[%s]", id);
        }
    }

    public List<EngineResponseVO> listAllEngine(){
        List<Engine> listAllEntity = engineRepository.listAll();
        List<EngineResponseVO> listEngineResponse = new ArrayList<>();

        for (Engine engine : listAllEntity) {
             listEngineResponse.add(EngineResponseVO.create(engine));
        }
        return listEngineResponse;
    }

    @Transactional
    public void delete(String code) {
        Engine listByCodeNumber = engineRepository.listByCodeNumber(code);
        if(listByCodeNumber == null) {
            throw new ServiceException("No Engine found for engine code[%s]", listByCodeNumber);
        } else {
            Car carReferences = carRepository.find("engine_id", listByCodeNumber.getId()).firstResult();
            if(carReferences !=null) {
                throw new ServiceException("Not delete engine with reference car", code);
            } else {
                engineRepository.delete(listByCodeNumber);
            }
        }
    }

}
