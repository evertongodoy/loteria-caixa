package com.everton.loterias.entrypoint.api.controller;

import com.everton.loterias.core.usecase.loterias.LoteriaUsecase;
import com.everton.loterias.entrypoint.api.controller.request.SalvarApostaRequest;
import com.everton.loterias.entrypoint.api.controller.response.SalvarApostaResponse;
import com.everton.loterias.entrypoint.api.dto.TipoLoteria;
import com.everton.loterias.entrypoint.api.mapper.LoteriaMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("loterias")
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoteriaController {

    private final LoteriaUsecase loteriaUsecase;

    @PostMapping("/criar-aposta")
    public ResponseEntity<SalvarApostaResponse> criarAposta(final @RequestBody SalvarApostaRequest request){
        return ResponseEntity.ok().body(LoteriaMapper.INSTANCE.toResponse(
                loteriaUsecase.salvarMinhaAposta(request.getTipoLoteria().getDescricao(),
                        new ArrayList<>(request.getNumerosAposta()),
                        request.isAtivo())
        ));
    }

    @PostMapping("/atualizar-base-sorteios/{loteria}")
    public ResponseEntity<String> atualizarBaseSorteios(@PathVariable(name = "loteria") final TipoLoteria tipoLoteria){
        loteriaUsecase.atualizarBaseLoteria(tipoLoteria.getDescricao());
        return ResponseEntity.ok().body("aaaaaaaaaaa");
    }

}
