document.addEventListener("DOMContentLoaded", () => {
    const selecionados = new Set();
    const campoTexto = document.getElementById("numerosSelecionados");
    const quantidadeSelecionados = document.getElementById("quantidadeSelecionados");

    document.querySelectorAll(".bola").forEach(bola => {
        bola.addEventListener("click", () => {
            const numero = bola.dataset.numero;

            if (selecionados.has(numero)) {
                selecionados.delete(numero);
                bola.classList.remove("selecionada");
            } else {
                selecionados.add(numero);
                bola.classList.add("selecionada");
            }

            campoTexto.value = Array.from(selecionados).sort((a, b) => a - b).join(", ");
            quantidadeSelecionados.textContent = selecionados.size; // Update the count
        });
    });
});