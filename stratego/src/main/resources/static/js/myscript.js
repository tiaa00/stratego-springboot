var socket = new SockJS('/clientHandler');

socket.onopen = function() {
    console.log('open');
    socket.send('test');
};
socket.onmessage = function(e) {
    console.log('message:', e.data);
    socket.close();
};
socket.onclose = function() {
    console.log('Connection closed');
};
socket.onerror = function(error) {
    console.log("An error occurred: " + error.message);
};