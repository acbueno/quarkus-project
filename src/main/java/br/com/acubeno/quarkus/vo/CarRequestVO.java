package br.com.acubeno.quarkus.vo;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.acubeno.quarkus.entity.Car;
import lombok.Data;

@Data
public class CarRequestVO {

    @JsonProperty("name-car")
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

    @JsonProperty("brand-name")
    private String brandName;

    @JsonProperty("engine-code")
    private String engineCode;

    public static CarRequestVO create(Car car) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        return modelMapper.map(car, CarRequestVO.class);
    }

}
