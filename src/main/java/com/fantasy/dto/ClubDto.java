package com.fantasy.dto;

import com.fantasy.domain.Photo;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class ClubDto {

    @NotBlank
    private String name;

    @NotNull
    private Photo logo;
}
