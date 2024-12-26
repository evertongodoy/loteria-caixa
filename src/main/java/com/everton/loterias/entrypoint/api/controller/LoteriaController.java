package com.everton.loterias.entrypoint.api.controller;

import com.everton.loterias.core.usecase.loterias.LoteriaUsecase;
import com.everton.loterias.entrypoint.api.controller.request.SalvarApostaRequest;
import com.everton.loterias.entrypoint.api.controller.response.*;
import com.everton.loterias.entrypoint.api.mapper.ApostaMapper;
import com.everton.loterias.entrypoint.api.mapper.CaixaResponseMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("loterias")
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoteriaController {

    private final LoteriaUsecase loteriaUsecase;

    @PostMapping("/criar-aposta")
    public ResponseEntity<MinhaApostaResponse> criarAposta(final @RequestBody SalvarApostaRequest request){
        var salvarApostaDomain = loteriaUsecase.salvarMinhaAposta(
                request.getTipoLoteria(), new ArrayList<>(request.getNumerosAposta()), request.isAtivo());
        return ResponseEntity.ok().body(ApostaMapper.INSTANCE.toResponse(salvarApostaDomain));
    }

    @GetMapping("/get-resultados/web/{jogo}")
    public ResponseEntity<CaixaResponse> getResultadoWeb(@PathVariable(name = "jogo") final String tipoLoteria,
                                                         @RequestParam(name = "numero", required = false) final Integer numeroSorteio){
        var caixaDomain = loteriaUsecase.recuperarSorteioWeb(tipoLoteria, numeroSorteio);
        return ResponseEntity.ok().body(CaixaResponseMapper.INSTANCE.toCaixaResponse(caixaDomain));
    }

    @GetMapping("/get-resultados/db/{jogo}")
    public ResponseEntity<CaixaResponse> getResultadoDataBase(@PathVariable(name = "jogo") final String tipoLoteria,
                                                              @RequestParam(name = "numero", required = false) final Integer numeroSorteio){
        var caixaDomain = loteriaUsecase.recuperarSorteioDataBase(tipoLoteria, numeroSorteio);
        return ResponseEntity.ok().body(CaixaResponseMapper.INSTANCE.toCaixaResponse(caixaDomain));
    }

    @PutMapping("/atualizar-base-dados/{jogo}")
    public ResponseEntity<AtualizacaoDataBaseResponse> atualizarDataBase(
            @PathVariable(value = "jogo") final String tipoJogo,
            @RequestParam(value = "timer",  defaultValue = "5_000", required = false) final Integer timeMillis,
            @RequestParam(value = "registros", defaultValue = "10_000", required = false) final Integer totalRegistros){
        var atualizacaoDataBaseDomain = loteriaUsecase.atualizarBaseLoteria(tipoJogo, timeMillis, totalRegistros);
        return ResponseEntity.ok().body(
                CaixaResponseMapper.INSTANCE.toDataBaseResponse(atualizacaoDataBaseDomain)
        );
    }

    @GetMapping("/consultar-minhas-apostas/{jogo}")
    public ResponseEntity<MinhaApostaResponse> consultarMinhasApostas(
            @PathVariable(value = "jogo") final String tipoJogo,
            @RequestParam(value = "uuid", required = false) final UUID uuid
    ){
        var minhasApostaDomain = loteriaUsecase.recuperarMinhasApostas(tipoJogo, uuid);
        return ResponseEntity.ok().body(ApostaMapper.INSTANCE.toResponse(minhasApostaDomain));
    }

    @GetMapping("/check-apostas/jogo/{jogo}/minha-aposta/{uuid}")
    public ResponseEntity<CheckApostasResponse> compararMinhaApostaComSorteio(@PathVariable(value = "jogo") final String tipoJogo,
                                                                              @PathVariable(value = "uuid") final UUID uuid){
        var chackApostasDomain = loteriaUsecase.validarAposta(tipoJogo, uuid, null);
        return ResponseEntity.ok().body(
                ApostaMapper.INSTANCE.toCheckApostaResponse(chackApostasDomain)
        );
    }

    @GetMapping("/check-apostas-simulada/jogo/{jogo}/simulacao/{simulacao}")
    public ResponseEntity<CheckApostasResponse> compararApostaSimuladaComSorteio(@PathVariable(value = "jogo") final String tipoJogo,
                                                                                 @PathVariable(value = "simulacao") final String numeros){
        var numerosSimulados = Arrays.stream(numeros.split("_"))
                .map(Integer::parseInt) // Converter cada elemento para Integer
                .distinct() // garantir que a lista final não contenha duplicatas
                .toList();
        var chackApostasDomain = loteriaUsecase.validarAposta(tipoJogo, null, numerosSimulados);
        return ResponseEntity.ok().body(
                ApostaMapper.INSTANCE.toCheckApostaResponse(chackApostasDomain)
        );
    }


}