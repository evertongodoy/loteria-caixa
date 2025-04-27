document.addEventListener("DOMContentLoaded", () => {
    const selecionados = new Set();
    const campoTexto = document.getElementById("numerosSelecionados");
    const quantidadeSelecionados = document.getElementById("quantidadeSelecionados");

    const botaoEnviar = document.querySelector(".btn-primary");
    botaoEnviar.disable = true

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









/*
document.addEventListener("DOMContentLoaded", () => {
    const selecionados = new Set();
    const campoTexto = document.getElementById("numerosSelecionados");
    const quantidadeSelecionados = document.getElementById("quantidadeSelecionados");
    const enviarButton = document.querySelector(".btn-primary"); // Select the "Enviar" button
    const jogo = document.querySelector("h3").textContent.split(" ")[0]; // Extract the game type

    // Handle ball selection
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

    // Handle button click to send data
    enviarButton.addEventListener("click", () => {
        const numeros = Array.from(selecionados);

        if (numeros.length === 0) {
            alert("Por favor, selecione ao menos um número.");
            return;
        }

        fetch("/validar-aposta", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ jogo, numeros })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao validar a aposta.");
            }
            return response.text(); // Expecting an HTML response
        })
        .then(html => {
            // Replace the current content with the response HTML
            document.querySelector("main.content").innerHTML = html;
        })
        .catch(error => {
            console.error("Erro:", error);
            alert("Ocorreu um erro ao validar a aposta.");
        });
    });
});
*/









/*










document.addEventListener("DOMContentLoaded", () => {
    const selecionados = new Set();
    const campoTexto = document.getElementById("numerosSelecionados");
    const quantidadeSelecionados = document.getElementById("quantidadeSelecionados");
    const enviarButton = document.querySelector(".btn-primary");
    const jogo = document.querySelector("h3").getAttribute("th:text").split(" ")[0]; // Extract the game type

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
            quantidadeSelecionados.textContent = selecionados.size;
        });
    });

    enviarButton.addEventListener("click", () => {
        const numeros = Array.from(selecionados);

        if (numeros.length === 0) {
            alert("Por favor, selecione ao menos um número.");
            return;
        }

        fetch(`/validar-aposta`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ jogo, numeros })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao validar a aposta.");
            }
            return response.json();
        })
        .then(data => {
            alert(`Resultado da validação: ${data.mensagem}`);
        })
        .catch(error => {
            console.error("Erro:", error);
            alert("Ocorreu um erro ao validar a aposta.");
        });
    });
});

























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
















*/
