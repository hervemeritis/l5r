package com.perso.web.dto;

public class ResumedSamuraiInformationDto {
    public String name;
    public String id;

    private ResumedSamuraiInformationDto(String samuraiFullName, String id) {
        this.name = samuraiFullName;
        this.id = id;
    }

    public static ResumedSamuraiInformationDto of(String samuraiFullName, String id) {
        return new ResumedSamuraiInformationDto(samuraiFullName, id);
    }
}
