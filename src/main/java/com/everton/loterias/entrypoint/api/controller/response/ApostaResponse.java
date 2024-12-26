package com.everton.loterias.entrypoint.api.controller.response;

import com.everton.loterias.entrypoint.api.dto.TipoLoteria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApostaResponse {

    private UUID uuid;
    private TipoLoteria tipoLoteria;
    private List<Integer> numeros;
    private LocalDate inicio;
    private boolean ativo;

}


