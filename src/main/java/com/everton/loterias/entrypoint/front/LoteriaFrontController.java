package com.everton.loterias.entrypoint.front;

import com.everton.loterias.core.domain.AtualizacaoDataBaseDomain;
import com.everton.loterias.core.domain.MaisSorteadosDomain;
import com.everton.loterias.core.usecase.loterias.LoteriaUsecase;
import com.everton.loterias.core.usecase.loteriasfront.LoteriaFrontUsecase;
import com.everton.loterias.entrypoint.api.controller.response.AtualizacaoDataBaseResponse;
import com.everton.loterias.entrypoint.api.mapper.CaixaResponseMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class LoteriaFrontController {

    private final LoteriaUsecase loteriaUsecase;

    private final LoteriaFrontUsecase loteriaFrontUsecase;

    private static final Logger logger = LoggerFactory.getLogger(LoteriaFrontController.class);

    @GetMapping("/")
    public String home(Model model) {
//        model.addAttribute("title", "Página Inicial");
//        model.addAttribute("content", "home :: content");
        // template "home" aponta para o arquivo home.html
        // fragment aponta para o bloco th:fragment="content" dentro do arquivo home.html
        model.addAttribute("title", "Página Inicial");
        model.addAttribute("template", "home");
        model.addAttribute("fragment", "content");
        return "layout";
    }

    @GetMapping("/selecao-loteria")
    public String page1(Model model) {
        model.addAttribute("title", "Selecao Loteria");
//        model.addAttribute("content", "page1 :: content");


        model.addAttribute("template", "selecao_loteria");
        model.addAttribute("fragment", "content");

        return "layout";
    }

    @PostMapping("/atualizar")
    public ResponseEntity<String> processarSelecao() {



        // Process the data as needed
        return ResponseEntity.ok("Dados recebidos com sucesso");
    }

    @PostMapping("/atualizar-base-dados/{jogo}")
    public String atualizarDataBase(
            Model model,
            @PathVariable(value = "jogo") final String tipoJogo,
            @RequestParam(value = "timer",  defaultValue = "5000", required = false) final Integer timeMillis,
            @RequestParam(value = "registros", defaultValue = "10000", required = false) final Integer totalRegistros){
//        var atualizacaoDatabaseResponse = CaixaResponseMapper.INSTANCE.toDataBaseResponse(
//                loteriaUsecase.atualizarBaseLoteria(tipoJogo, timeMillis, totalRegistros));
        AtualizacaoDataBaseResponse a = AtualizacaoDataBaseResponse.builder()
                .totalSorteiosCaixa(20)
                .sorteiosCaixa(List.of(1234, 1235, 1236, 1237, 1238, 1239, 1240, 1241, 1242, 1243, 1244, 1245, 1246, 1247, 1248, 1249, 1250, 1251, 1252, 1253, 2554, 1255, 1256, 1257, 1258, 1259, 1260))
                .build();
        model.addAttribute("template", "base_atualizada");
        model.addAttribute("fragment", "content");
        model.addAttribute("sei_la", "nao_sei");
        model.addAttribute("jogo", tipoJogo);
        model.addAttribute("dados", "agora-foi");
        model.addAttribute("response", a);
        return "layout";
    }

    @GetMapping("/aposta-simulada/{jogo}")
    public String apostaSimulada(
            Model model,
            @PathVariable(value = "jogo") final String tipoJogo){
        model.addAttribute("template", "aposta_simulada");
        model.addAttribute("fragment", "content");
        model.addAttribute("jogo", tipoJogo.toLowerCase());
        var numerosDisponiveis = loteriaFrontUsecase.numerosDisponiveis(tipoJogo);
        model.addAttribute("numeros_disponiveis", numerosDisponiveis);
        return "layout";
    }

    @GetMapping("/consulta-mais-sorteados/{jogo}")
    public String maisSorteados(
            Model model,
            @PathVariable(value = "jogo") final String tipoJogo){
        model.addAttribute("template", "mais_sorteados");
        model.addAttribute("fragment", "content");
        model.addAttribute("jogo", tipoJogo);
        MaisSorteadosDomain sorteadosDomain = loteriaUsecase.recuperarMaisSorteados(tipoJogo, null);
        model.addAttribute("sorteados", sorteadosDomain);
        return "layout";
    }

}