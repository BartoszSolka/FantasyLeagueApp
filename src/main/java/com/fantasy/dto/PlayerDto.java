package com.fantasy.dto;

import com.fantasy.domain.Photo;
import com.fantasy.enums.Position;
import lombok.Data;

@Data
public class PlayerDto {

    private String name;

    private String surname;

    private Photo photo;

    private Position position;

    private Long clubId;

    private Integer shirtNumber;

    private Integer price;
}
