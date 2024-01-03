package com.perso.web.dto;

import com.herve.l5r.system.model.Attribut;

public class AttributDto {
    public int intelligence;
    public int agilite;
    public int feu;
    public int reflexe;
    public int intuition;
    public int air;
    public int perception;
    public int force;
    public int eau;
    public int volonte;
    public int constitution;
    public int terre;
    public int vide;

    public static AttributDto from(Attribut attribut) {
        AttributDto attributDto = new AttributDto();
        attributDto.agilite = attribut.agilite;
        attributDto.intelligence = attribut.intelligence;
        attributDto.feu = attribut.feu();
        attributDto.reflexe = attribut.reflexe;
        attributDto.intuition = attribut.intuition;
        attributDto.air = attribut.air();
        attributDto.perception = attribut.perception;
        attributDto.force = attribut.force;
        attributDto.eau = attribut.eau();
        attributDto.volonte = attribut.volonte;
        attributDto.constitution = attribut.constitution;
        attributDto.terre = attribut.terre();
        attributDto.vide = attribut.vide;
        return attributDto;
    }
}
