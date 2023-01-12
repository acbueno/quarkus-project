package br.com.acbueno.quarkus.vo;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.acbueno.quarkus.entity.Car;
import lombok.Data;

@Data
public class CarResponseVO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("model")
    private String model;

    @JsonProperty("year-factory")
    private int yearFactory;

    @JsonProperty("year-model")
    private int yearModel;

    @JsonProperty("color")
    private String color;

    @JsonProperty("chassis-number")
    private String chassisNumber;

    @JsonProperty("brand")
    private BrandResponseVO brand;

    @JsonProperty("engine")
    private EngineResponseVO engine;

    public static CarResponseVO create(Car car) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(car, CarResponseVO.class);
    }

}
