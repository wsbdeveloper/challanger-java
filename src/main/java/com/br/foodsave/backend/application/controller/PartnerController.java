package com.br.foodsave.backend.application.controller;

import com.br.foodsave.backend.infrastructure.repository.PartnerEntity;
import com.br.foodsave.backend.service.PartnerService;
import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partner")
public class PartnerController {
    private PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid PartnerEntity partner){
        try {
            PartnerEntity serviceResponse = partnerService.create(partner);
            return new ResponseEntity(serviceResponse, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    List<PartnerEntity> list(){
        return partnerService.list();
    }

    @GetMapping(path = "/consult")
    ResponseEntity get(@RequestParam(name = "id") int id){

        try {
            Optional<PartnerEntity> consult = partnerService.get(id);

            if (consult.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(consult);
        } catch (EmptyResultDataAccessException err){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PartnerEntity().info("Id Não Encontrado! Cadastre algum parceiro e volte a consultar."));
        }
    }

    @DeleteMapping
    ResponseEntity delete(@RequestParam(name = "id") int id){
        var delete = partnerService.deletePartner(id);

        if (!delete){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().build();
    }
}
