package br.com.acbueno.quarkus.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.modelmapper.ModelMapper;

import br.com.acbueno.quarkus.vo.CarRequestVO;
import lombok.Data;

@Entity
@Table(name = "car")
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "year_factory")
    private int yearFactory;

    @Column(name = "year_model")
    private int yearModel;

    @Column(name = "color")
    private String color;

    @Column(name = "chassis_number", length = 200, unique = true)
    private String chassisNumber;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public static Car create(CarRequestVO carVO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        return modelMapper.map(carVO, Car.class);
    }

}
