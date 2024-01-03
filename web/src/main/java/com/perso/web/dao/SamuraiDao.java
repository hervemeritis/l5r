package com.perso.web.dao;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.sample.SamuraiFactory;
import com.perso.web.model.SamuraiWrapper;

import javax.inject.Named;
import java.util.Map;
import java.util.stream.Stream;

@Named
public class SamuraiDao implements SamuraiRetriever {
    private final static Map<String, Samurai> samurais = Map.of("kakita", SamuraiFactory.kakita(), "mirumoto", SamuraiFactory.mirumoto(), "matsu", SamuraiFactory.matsu());


    @Override
    public Stream<SamuraiWrapper> retrieveAll() {
        return samurais.entrySet()
                       .stream()
                       .map(entry -> SamuraiWrapper.of(entry.getKey(), entry.getValue()));
    }

    @Override
    public Samurai retrieve(String id) {
        return samurais.get(id);
    }
}
