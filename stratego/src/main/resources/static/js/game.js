const sessionId = "123"; // Replace with the actual sessionId
var socket = new SockJS('/gs-guide-websocket');
var stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
    console.log('Stomp client connected');
    stompClient.subscribe(`/topic/game/${sessionId}`, function (result) {
        console.log("server replies to you:", result.body);
    });
});

// Define a function to send a move message
function sendMoveMessage() {

  const moveMessage = {
    sessionId: sessionId,
    sourceX: 5,
    sourceY: 5,
    targetX: 6,
    targetY: 6,
  };

  // Convert the move message to JSON
  const messageJson = JSON.stringify(moveMessage);

    stompClient.send('/app/game/' + sessionId + '/move', {}, messageJson);
}

// if "from" == httpsession then print "You: " else "Name:"