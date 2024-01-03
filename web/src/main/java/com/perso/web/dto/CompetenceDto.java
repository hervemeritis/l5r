package com.perso.web.dto;

import com.herve.l5r.system.model.competence.Competence;
import com.herve.l5r.system.model.competence.Emphasis;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompetenceDto {
    public String name;
    public int value;

    public static CompetenceDto from(Competence competence) {
        CompetenceDto competenceDto = new CompetenceDto();
        competenceDto.name = competence.name.competenceName();
        if (!competence.emphasis().isEmpty()) {
            competenceDto.name = competenceDto.name + "(" + competence.emphasis().stream().map(emp -> emp.emphasisName() + " ").reduce(String::concat).orElse("") + ")";
        }
        competenceDto.value = competence.value();
        return competenceDto;
    }
}
