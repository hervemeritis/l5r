package com.perso.web.controller;

import com.perso.web.service.SamuraiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/samurai")
@CrossOrigin
public class PersonnageController {

    private final SamuraiService samuraiService;

    @Inject
    public PersonnageController(SamuraiService samuraiService) {
        this.samuraiService = samuraiService;
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> retrieveAllSamurai() {
        return ResponseEntity.ok(samuraiService.retrieveAll().collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> retrieveSamurai(@PathVariable("id") String id) {
        return ResponseEntity.ok(samuraiService.retrieve(id));
    }
}
