package com.perso.web.dto;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.model.avantage.Avantage;

import java.util.List;
import java.util.stream.Collectors;

public class AvantageDto {
    public String description;

    public AvantageDto(String description) {
        this.description = description;
    }

    public static List<AvantageDto> from(Samurai samurai) {
        return samurai.avantages().stream().map(avt -> new AvantageDto(avt.display(samurai))).collect(Collectors.toList());
    }
}
