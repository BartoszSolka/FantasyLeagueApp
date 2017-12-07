package com.fantasy.dto;

import com.fantasy.domain.Club;
import com.fantasy.domain.Photo;
import com.fantasy.enums.Position;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
public class PlayerDto {

    private String name;

    private String surname;

    @OneToOne
    private Photo photo;

    @Enumerated(EnumType.STRING)
    private Position position;

    @ManyToOne
    private Club club;

    private Integer shirtNumber;

    private Integer price;
}
