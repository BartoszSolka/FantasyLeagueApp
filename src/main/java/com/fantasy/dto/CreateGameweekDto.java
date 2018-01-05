package com.fantasy.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateGameweekDto {

    private String name;

    private List<CreateMatchDto> matches;
}
