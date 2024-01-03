package com.perso.web.dto;

import com.herve.l5r.system.model.Samurai;

import java.util.List;
import java.util.stream.Collectors;

public class SamuraiDto {
    public String name;
    public double honneur;
    public double gloire;
    public double statut;
    public AttributDto attribut;
    public List<SchoolDto> schools;
    public List<CompetenceDto> competences;
    public List<AvantageDto> avantages;

    public static SamuraiDto from(Samurai samurai) {
        SamuraiDto samuraiDto = new SamuraiDto();
        samuraiDto.name = samurai.fullName();
        samuraiDto.gloire = samurai.gloire;
        samuraiDto.honneur = samurai.honneur;
        samuraiDto.statut = samurai.statut;
        samuraiDto.attribut = AttributDto.from(samurai.attributs);
        samuraiDto.competences = samurai.competences().stream().map(CompetenceDto::from).collect(Collectors.toList());
        samuraiDto.avantages = AvantageDto.from(samurai);
        samuraiDto.schools = samurai.schools.stream().map(SchoolDto::from).collect(Collectors.toList());
        return samuraiDto;
    }
}
