package br.com.acbueno.quarkus.vo;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.acbueno.quarkus.entity.Engine;
import lombok.Data;

@Data
public class EngineResponseVO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("capacity")
    private Double capacity;

    @JsonProperty("hp")
    private int hp;

    public static EngineResponseVO create(Engine engine) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(engine, EngineResponseVO.class);
    }

}
