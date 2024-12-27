package com.everton.loterias.core.usecase.loterias.impl.strategy.impl;

import com.everton.loterias.core.domain.*;
import com.everton.loterias.core.gateway.client.LoteriaGatewayWeb;
import com.everton.loterias.core.gateway.database.LoteriaGateway;
import com.everton.loterias.core.usecase.loterias.impl.strategy.LoteriaStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LotomaniaStrategyImpl implements LoteriaStrategy {

    private final LoteriaGateway loteriaGateway;
    private final LoteriaGatewayWeb loteriaGatewayWeb;
    private static final TipoLoteriaDomain TIPO_LOTERIA = TipoLoteriaDomain.LOTOMANIA;

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
    public AtualizacaoDataBaseDomain atualizarDataBaseCaixaSorteio(final Integer timerMillis, final Integer qtdeRegistros) {
        var ultimoSorteioCaixaWeb = loteriaGatewayWeb.recuperarSorteio(TIPO_LOTERIA, null)
                .getResultados().get(0).getNumeroSorteio();
        var resultadosCaixaDomain =
                loteriaGateway.recuperarSorteio(TIPO_LOTERIA, null).getResultados();
        var ultimoSorteioCaixaDb = resultadosCaixaDomain.isEmpty() ? 0 : resultadosCaixaDomain.get(resultadosCaixaDomain.size() - 1).getNumeroSorteio();
        return this.salvarCaixaSorteios(ultimoSorteioCaixaWeb, ultimoSorteioCaixaDb, timerMillis, qtdeRegistros);
    }

    @Override
    public MinhaApostaDomain recuperarMinnasApostasCaixa(UUID uuid) {
        return loteriaGateway.recuperarApostasCaixa(TIPO_LOTERIA, uuid);
    }

    @Override
    public CheckApostasDomain checarApostaVencedora(final UUID uuid, final List<Integer> apostaSimulada) {
        ApostaDomain minhaAposta;
        if(Objects.isNull(uuid)){
            minhaAposta = ApostaDomain.builder().numeros(apostaSimulada).build();
        } else {
            var minhaApostaDomain = loteriaGateway.recuperarApostasCaixa(TIPO_LOTERIA, uuid);
            minhaAposta = minhaApostaDomain.getApostasDomain().get(0);
        }

        var sorteios = loteriaGateway.recuperarSorteio(TIPO_LOTERIA, null).getResultados();

        var apostasChecadas = new ArrayList<ApostaCheckedDomain>();

        sorteios.forEach(resultado -> {
            var numerosCoincidentes = new ArrayList<Integer>();
            for(Integer meuNumero : minhaAposta.getNumeros()){
                if(resultado.getListaDezenas().contains(meuNumero)){
                    numerosCoincidentes.add(meuNumero);
                }
            }
            if(numerosCoincidentes.size() >= 15){
                var apostaChecada = ApostaCheckedDomain.builder()
                        .dataSorteio(resultado.getDataApuracao())
                        .numeroSorteio(resultado.getNumeroSorteio())
                        .totalAcertos(numerosCoincidentes.size())
                        .numerosCorretos(numerosCoincidentes)
                        .tipoJogo(TIPO_LOTERIA)
                        .build();
                apostasChecadas.add(apostaChecada);
            }
        });
        return CheckApostasDomain.builder()
                .totalJogosComAcerto(apostasChecadas.size())
                .maioresAcertos(montarMaioresAcertos(apostasChecadas))
                .apostasCheckadas(apostasChecadas)
                .build();
    }

    private void validarTamanhoLista(List<Integer> numeros){
        if(Objects.isNull(numeros) || numeros.isEmpty() || numeros.size() < 20){
            throw new RuntimeException("Quantidade minima de numeros deve ser 20");
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
                salvarCaixaSorteios(ultimoSorteioCaixaWeb, proximoSorteioBaseDados, timerMillis, (qtdeRegistros-qtdeAtualizados));
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