package com.perso.web.dao;

import com.herve.l5r.system.model.Samurai;
import com.perso.web.model.SamuraiWrapper;

import java.util.List;
import java.util.stream.Stream;

public interface SamuraiRetriever {

    Stream<SamuraiWrapper> retrieveAll();

    Samurai retrieve(String id);
}
