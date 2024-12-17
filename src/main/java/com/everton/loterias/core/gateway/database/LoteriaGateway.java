package com.everton.loterias.core.gateway.database;

import com.everton.loterias.core.domain.LoteriaDomain;

public interface LoteriaGateway {

    LoteriaDomain salvarMinhaLoteria(final LoteriaDomain domain);

}
