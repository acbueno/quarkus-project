package br.com.acubeno.quarkus.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.acubeno.quarkus.entity.Engine;
import br.com.acubeno.quarkus.exception.ServiceException;
import br.com.acubeno.quarkus.repository.EngineRepository;
import br.com.acubeno.quarkus.vo.EngineRequestVO;
import br.com.acubeno.quarkus.vo.EngineResponseVO;

@ApplicationScoped
public class EngineService {

    @Inject
    EngineRepository engineRepository;


    public EngineResponseVO getEngineByCodeNumber(String codeNumber) {
        return EngineResponseVO.create(engineRepository.listByCodeNumber(codeNumber));
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
            throw new ServiceException("No Engine found for engineCodeNumber[%s]", engineVO.getCode());
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

}
