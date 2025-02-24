let currentPage = 1;
let currentMonth = 'March';
let currentSearch = '';
let barChartInstance = null;
let pieChartInstance = null;

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('monthSelect').addEventListener('change', updateDashboard);
    document.getElementById('searchInput').addEventListener('input', updateDashboard);
    document.getElementById('prevBtn').addEventListener('click', () => changePage(-1));
    document.getElementById('nextBtn').addEventListener('click', () => changePage(1));

    loadInitialData();
});

async function loadInitialData() {
    await updateTransactions();
    await updateStatistics();
    await updateCharts();
}

async function updateDashboard() {
    currentMonth = document.getElementById('monthSelect').value;
    currentSearch = document.getElementById('searchInput').value;
    currentPage = 1;

    await Promise.all([updateTransactions(), updateStatistics(), updateCharts()]);
}

async function updateTransactions() {
    try {
        const response = await fetch(
            `/transactions?month=${currentMonth}&search=${currentSearch}&page=${currentPage}`
        );
        const data = await response.json();

        const tbody = document.getElementById('tableBody');
        tbody.innerHTML = data.content.map(transaction => `
            <tr class="border-b">
                <td class="p-3">${transaction.title}</td>
                <td class="p-3">${transaction.description}</td>
                <td class="p-3">₹${transaction.price.toFixed(2)}</td>
                <td class="p-3">${transaction.category}</td>
                <td class="p-3">${transaction.sold ? 'Yes' : 'No'}</td>
            </tr>
        `).join('');

        document.getElementById('pageInfo').textContent = `Page ${currentPage}`;
    } catch (error) {
        console.error('Error fetching transactions:', error);
    }
}

async function updateStatistics() {
    try {
        const response = await fetch(`/statistics?month=${currentMonth}`);
        const stats = await response.json();

        document.getElementById('statsContainer').innerHTML = `
            <div class="p-4 bg-white rounded shadow">
                <h3 class="text-gray-500">Total Sale</h3>
                <p class="text-2xl">₹${stats.totalSaleAmount.toFixed(2)}</p>
            </div>
            <div class="p-4 bg-white rounded shadow">
                <h3 class="text-gray-500">Sold Items</h3>
                <p class="text-2xl">${stats.totalSoldItems}</p>
            </div>
            <div class="p-4 bg-white rounded shadow">
                <h3 class="text-gray-500">Unsold Items</h3>
                <p class="text-2xl">${stats.totalUnsoldItems}</p>
            </div>
        `;
    } catch (error) {
        console.error('Error fetching statistics:', error);
    }
}

async function updateCharts() {
    try {
        // Destroy existing chart instances
        if (barChartInstance) barChartInstance.destroy();
        if (pieChartInstance) pieChartInstance.destroy();

        // Fetch new chart data
        const barResponse = await fetch(`/bar-chart?month=${currentMonth}`);
        const barData = await barResponse.json();

        const pieResponse = await fetch(`/pie-chart?month=${currentMonth}`);
        const pieData = await pieResponse.json();

        // Create new charts
        barChartInstance = new Chart(document.getElementById('barChart'), {
            type: 'bar',
            data: {
                labels: barData.map(item => item.range),
                datasets: [{
                    label: 'Number of Items',
                    data: barData.map(item => item.count),
                    backgroundColor: '#4f46e5'
                }]
            }
        });

        pieChartInstance = new Chart(document.getElementById('pieChart'), {
            type: 'pie',
            data: {
                labels: pieData.map(item => item.category),
                datasets: [{
                    data: pieData.map(item => item.count),
                    backgroundColor: [
                        '#4f46e5', '#60a5fa', '#34d399',
                        '#fbbf24', '#f87171', '#a78bfa',
                        '#fb923c', '#38bdf8', '#10b981'
                    ]
                }]
            }
        });
    } catch (error) {
        console.error('Error updating charts:', error);
    }
}

function changePage(delta) {
    currentPage = Math.max(1, currentPage + delta);
    updateTransactions();
}