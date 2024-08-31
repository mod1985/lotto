// JavaScript for loading items on index.html
document.addEventListener('DOMContentLoaded', () => {
    fetch('/api/lottery/items')
        .then(response => response.json())
        .then(items => {
            const itemsList = document.getElementById('items-list');
            items.forEach(item => {
                const itemDiv = document.createElement('div');
                itemDiv.classList.add('item');
                itemDiv.innerHTML = `
                    <h3>${item.name}</h3>
                    <p>Type: ${item.type}</p>
                    <p>Ticket Price: $${item.ticketPrice}</p>
                    <p>Tickets Sold: ${item.ticketsSold}</p>
                    <p>Minimum Tickets to Start Draw: ${item.minTickets}</p>
                    <p>Payment Info: ${item.paymentInfo}</p>
                    <img src="images/${item.image}" alt="${item.name}">
                `;
                itemsList.appendChild(itemDiv);
            });
        });
});