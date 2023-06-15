var socket = new WebSocket("ws://localhost:8080/chat");

socket.onopen = function() {
  // Connection is established
  var message = {
    content: "Hello server!"
  };
  socket.send(JSON.stringify(message));
};

socket.onmessage = function(event) {
  // Handle incoming messages from the server
  var receivedMessage = JSON.parse(event.data);
  console.log("Received message from server:", receivedMessage);
};

socket.onclose = function(event) {
  // Handle connection closure
  console.log("WebSocket connection closed with code:", event.code);
};
