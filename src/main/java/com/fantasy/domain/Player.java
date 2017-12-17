package com.fantasy.domain;

import com.fantasy.enums.Position;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;

    @OneToOne
    private Photo photo;

    @Enumerated(EnumType.STRING)
    private Position position;

    @ManyToOne
    private Club club;

    private int shirtNumber;

    private int price;

    private int points;

    public Player() {
    }

    public Player(String name, String surname, Position position, Club club, int shirtNumber) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.club = club;
        this.shirtNumber = shirtNumber;
    }
}
