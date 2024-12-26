package com.everton.loterias.core.usecase.loterias.impl.strategy.impl;

import com.everton.loterias.core.domain.*;
import com.everton.loterias.core.gateway.client.LoteriaGatewayWeb;
import com.everton.loterias.core.gateway.database.LoteriaGateway;
import com.everton.loterias.core.usecase.loterias.impl.strategy.LoteriaStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LotofacilStrategyImpl implements LoteriaStrategy {

    private final LoteriaGateway loteriaGateway;
    private final LoteriaGatewayWeb loteriaGatewayWeb;
    private static final TipoLoteriaDomain TIPO_LOTERIA = TipoLoteriaDomain.LOTOFACIL;

    @Override
    public boolean seletorLoteria(final TipoLoteriaDomain tipoLoteriaDomain) {
        return TIPO_LOTERIA.equals(tipoLoteriaDomain);
    }

    @Override
    public MinhaApostaDomain salvarAposta(final ApostaDomain domain) {
        validarTamanhoLista(domain.getNumeros());
        return loteriaGateway.salvarMinhaLoteria(domain);
    }

    @Override
    public CaixaDomain recuperarSorteioWeb(Integer numero) {
        return loteriaGatewayWeb.recuperarSorteio(TIPO_LOTERIA, numero);
    }

    @Override
    public CaixaDomain recuperarSorteioDataBase(Integer numero) {
        return loteriaGateway.recuperarSorteio(TIPO_LOTERIA, numero);
    }

    @Override
    public AtualizacaoDataBaseDomain atualizarDataBaseCaixaSorteio(Integer timerMillis, Integer qtdeRegistros) {
        var ultimoSorteioCaixaWeb = loteriaGatewayWeb.recuperarSorteio(TIPO_LOTERIA, null)
                .getResultados().get(0).getNumeroSorteio();
        var resultadosCaixaDomain =
                loteriaGateway.recuperarSorteio(TIPO_LOTERIA, null).getResultados();
        var ultimoSorteioCaixaDb = resultadosCaixaDomain.isEmpty() ? 0 : resultadosCaixaDomain.get(resultadosCaixaDomain.size() - 1).getNumeroSorteio();
        return this.salvarCaixaSorteios(ultimoSorteioCaixaWeb, ultimoSorteioCaixaDb, timerMillis, qtdeRegistros);
    }

    private void validarTamanhoLista(List<Integer> numeros){
        if(Objects.isNull(numeros) || numeros.isEmpty() || numeros.size() < 15){
            throw new RuntimeException("Quantidade minima de numeros deve ser 15");
        }
    }

    private AtualizacaoDataBaseDomain salvarCaixaSorteios(final Integer ultimoSorteioCaixaWeb,
                                                          final Integer ultimoSorteioCaixaDb,
                                                          final Integer timerMillis,
                                                          final Integer qtdeRegistros) {
        var sorteiosCaixaSalvo = new ArrayList<Integer>();
        var qtdeAtualizados = 0;
        var proximoSorteioBaseDados = ultimoSorteioCaixaDb + 1;

        while (proximoSorteioBaseDados <= ultimoSorteioCaixaWeb && qtdeAtualizados < qtdeRegistros){
            try {
                Thread.sleep(timerMillis);
                var sorteioCaixaWebDomain =
                        loteriaGatewayWeb.recuperarSorteio(TIPO_LOTERIA, proximoSorteioBaseDados).getResultados().get(0);
                regularizarAtributosWeb(sorteioCaixaWebDomain);
                loteriaGateway.salvarSorteio(sorteioCaixaWebDomain);
                sorteiosCaixaSalvo.add(proximoSorteioBaseDados);
                proximoSorteioBaseDados++;
                qtdeAtualizados++;
            } catch (Exception e) {
                System.out.println("**********************************************************");
                System.out.println("**************** REINICIANDO PERSISTENCIA ****************");
                System.out.println("**********************************************************");
                salvarCaixaSorteios(ultimoSorteioCaixaWeb, (proximoSorteioBaseDados-1), timerMillis, (qtdeRegistros-qtdeAtualizados));
            }

        }
        return AtualizacaoDataBaseDomain.builder()
                .totalSorteiosCaixa(sorteiosCaixaSalvo.size())
                .sorteiosCaixa(sorteiosCaixaSalvo)
                .build();
    }

    private void regularizarAtributosWeb(ResultadoDomain resultadoDomain){
        resultadoDomain.setUuid(UUID.randomUUID());
        resultadoDomain.setNomeMunicipioUFSorteio(resultadoDomain.getNomeMunicipioUFSorteio().isEmpty() ?
                "N/A" : resultadoDomain.getNomeMunicipioUFSorteio());
        resultadoDomain.setTipoJogo(resultadoDomain.getTipoJogo().toUpperCase());
        resultadoDomain.setNomeMunicipioUFSorteio(resultadoDomain.getNomeMunicipioUFSorteio().toUpperCase());
    }

}