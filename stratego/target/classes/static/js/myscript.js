var socket = new SockJS('/myHandler');

socket.onopen = function() {
    const message = 'sending from client';
    console.log('onopen, sending message: ' + message);
    socket.send(message);
};
socket.onmessage = function(e) {
    console.log('onmessage from server, message: ' + e.data);
};
socket.onclose = function() {
    console.log('onclose');
};
socket.onerror = function(error) {
    console.log('onclose, error: ' + error.message);
};