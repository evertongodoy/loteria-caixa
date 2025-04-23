const sorteios = /*[[${response.sorteiosCaixa}]]*/ []; // Replace with Thymeleaf variable
const rowsPerPage = 5;
const totalPages = Math.ceil(sorteios.length / rowsPerPage);
const tbody = document.getElementById('sorteios-tbody');
const pageSelect = document.getElementById('page-select');
const inicioBtn = document.getElementById('inicio-btn');
const finalBtn = document.getElementById('final-btn');

// Populate the ComboBox with page numbers
for (let i = 1; i <= totalPages; i++) {
    const option = document.createElement('option');
    option.value = i;
    option.textContent = `Página ${i}`;
    pageSelect.appendChild(option);
}

// Function to render rows for the current page
function renderPage(page) {
    tbody.innerHTML = ''; // Clear existing rows
    const start = (page - 1) * rowsPerPage;
    const end = Math.min(start + rowsPerPage, sorteios.length);
    for (let i = start; i < end; i++) {
        const row = document.createElement('tr');
        const cell = document.createElement('td');
        cell.textContent = sorteios[i];
        row.appendChild(cell);
        tbody.appendChild(row);
    }
    pageSelect.value = page; // Update ComboBox selection
}

// Event listeners for buttons
inicioBtn.addEventListener('click', () => renderPage(1));
finalBtn.addEventListener('click', () => renderPage(totalPages));
pageSelect.addEventListener('change', (e) => renderPage(Number(e.target.value)));

// Initialize with the first page
renderPage(1);