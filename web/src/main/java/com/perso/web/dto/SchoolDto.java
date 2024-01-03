package com.perso.web.dto;

import com.herve.l5r.system.model.school.FamilySchool;
import com.herve.l5r.system.model.school.School;

public class SchoolDto {
    public String name;
    public int rang;

    public static SchoolDto from(School school) {
        SchoolDto schoolDto = new SchoolDto();
        schoolDto.name = school.familySchool.schoolName();
        schoolDto.rang = school.rank;
        return schoolDto;
    }
}
