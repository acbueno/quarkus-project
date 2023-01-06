package br.com.acubeno.quarkus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.modelmapper.ModelMapper;
import br.com.acubeno.quarkus.vo.EngineRequestVO;
import lombok.Data;

@Entity
@Table(name = "engine")
@Data
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "capacity")
    private Double capacity;

    @Column(name = "hp")
    private int hp;

    public static Engine create(EngineRequestVO engineVO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        return modelMapper.map(engineVO, Engine.class);
    }

}
