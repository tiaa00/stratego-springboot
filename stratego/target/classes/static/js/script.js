function handleDragStart(event) {
    event.dataTransfer.set("text/plain", event.target.id);
}

function handleDragOver(event) {
    event.preventDefault();
}

function handleDragEnter(event) {
    event.preventDefault();
    event.target.classList.add("drag-over");
}

function handleDragLeave(event) {
    event.target.classList.remove("drag-over");
}

function handleDrop(event) {
    event.preventDefault();
    const data = event.dataTransfer.getData("text/plain");
    const draggedElement = document.getElementById(data);
    event.target.appendChild(draggedElement);
    event.target.classList.remove("drag-over");
}

function populateTable(data) {
    const count = 0;
    const tableBody = document.getElementById('data-table-body');
    tableBody.innerHTML = ''; // Clear the table body first

    data.forEach(rowData => {
        const row = document.createElement('tr');

        const head = document.createElement('th');
        head.setAttribute('scope', 'row');
        head.textContent = ++count;
        row.appendChild(head);

        // Create and populate cells for each column
        const roomName = document.createElement('td');
        roomName.textContent = rowData.roomName;
        row.appendChild(roomName);

        const host = document.createElement('td');
        host.textContent = rowData.host;
        row.appendChild(host);

        const playerInRoomCount = document.createElement('td');
        playerInRoomCount.textContent = rowData.playerInRoomCount;
        row.appendChild(playerInRoomCount);

        const action = document.createElement('td');
        const button = document.createElement('a');
        button.setAttribute('class', 'btn btn-primary');
        button.setAttribute('id', rowData.roomId);
        button.textContent = 'Join';
        action.appendChild(button);
        row.appendChild(action);

        // Append the row to the table body
        tableBody.appendChild(row);
    });
}




