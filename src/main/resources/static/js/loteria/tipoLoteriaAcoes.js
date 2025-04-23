function toggleAcaoDesejada() {
    const tipoLoteria = document.getElementById('tipoLoteria');
    const acaoDesejada = document.getElementById('acaoDesejada');
    acaoDesejada.disabled = tipoLoteria.value === "";
}

function redirecionarParaAtualizacao(select) {
    const acao = select.value;
    const tipo = document.getElementById("tipoLoteria").value;

    if (acao === "ATUALIZAR_BASE" && tipo) {
        // Cria um form temporário para fazer POST
        const form = document.createElement("form");
        form.method = "POST";
        form.action = "/atualizar-base-dados/" + encodeURIComponent(tipo);

        // (Opcional) parâmetros adicionais (como timeMillis e totalRegistros)
        const timeInput = document.createElement("input");
        timeInput.type = "hidden";
        timeInput.name = "timer";
        timeInput.value = "6000";
        form.appendChild(timeInput);

        const regInput = document.createElement("input");
        regInput.type = "hidden";
        regInput.name = "registros";
        regInput.value = "4000";
        form.appendChild(regInput);

        document.body.appendChild(form);
        form.submit();
    } else if (acao === "APOSTA_SIMULADA" && tipo) {
        // Cria um form temporário para fazer GET
        const form = document.createElement("form");
        form.method = "GET";
        form.action = "/aposta-simulada/" + encodeURIComponent(tipo);

        document.body.appendChild(form);
        form.submit();
    } else if (acao === "MAIS_SORTEADOS" && tipo) {
        // Cria um form temporário para fazer GET
        const form = document.createElement("form");
        form.method = "GET";
        form.action = "/consulta-mais-sorteados/" + encodeURIComponent(tipo);

        document.body.appendChild(form);
        form.submit();
    }
}