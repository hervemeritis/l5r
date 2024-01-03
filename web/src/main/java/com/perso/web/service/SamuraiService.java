package com.perso.web.service;


import com.perso.web.dao.SamuraiRetriever;
import com.perso.web.dto.ResumedSamuraiInformationDto;
import com.perso.web.dto.SamuraiDto;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Named
public class SamuraiService {

    private final SamuraiRetriever samuraiRetriever;

    @Inject
    public SamuraiService(SamuraiRetriever samuraiRetriever) {
        this.samuraiRetriever = samuraiRetriever;
    }

    public Stream<ResumedSamuraiInformationDto> retrieveAll() {
        return samuraiRetriever.retrieveAll()
                               .map(wrapper -> ResumedSamuraiInformationDto.of(wrapper.samurai.fullName(), wrapper.id));
    }

    public SamuraiDto retrieve(String id) {
        return SamuraiDto.from(samuraiRetriever.retrieve(id));
    }
}
