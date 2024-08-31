document.getElementById('add-item-form').addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(document.getElementById('add-item-form'));

    fetch('/api/lottery/add', {
        method: 'POST',
        body: formData
    }).then(response => response.text()).then(result => {
        alert(result);
        if (result === 'Item added successfully!') {
            window.location.href = 'index.html';
        }
    }).catch(error => {
        console.error('Error:', error);
    });
});