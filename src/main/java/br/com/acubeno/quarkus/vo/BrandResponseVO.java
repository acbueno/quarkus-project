package br.com.acubeno.quarkus.vo;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.acubeno.quarkus.entity.Brand;
import lombok.Data;

@Data
public class BrandResponseVO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("brand-name")
    private String brandName;

    public static BrandResponseVO create(Brand brand) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        return modelMapper.map(brand, BrandResponseVO.class);
    }

}
