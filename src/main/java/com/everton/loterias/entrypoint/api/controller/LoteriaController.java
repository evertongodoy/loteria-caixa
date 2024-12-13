package com.everton.loterias.entrypoint.api.controller;

import com.everton.loterias.core.usecase.loterias.LoteriaUsecase;
import com.everton.loterias.entrypoint.api.controller.request.SalvarApostaRequest;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("loterias")
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoteriaController {

    private final LoteriaUsecase loteriaUsecase;

    @PostMapping("/criar-aposta")
    public ResponseEntity<String> criarAposta(final @RequestBody SalvarApostaRequest request){
        System.out.println(request.getNumerosAposta());
        System.out.println(request.getTipoLoteria());
        loteriaUsecase.salvarMinhaLoteria(request.getTipoLoteria().getDescricao(), new ArrayList<>(request.getNumerosAposta()));
        return ResponseEntity.ok().body("aaaaaa");
    }
}
