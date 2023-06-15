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




