const sessionId = "123"; // Replace with the actual sessionId
var socket = new SockJS('/gs-guide-websocket');
var stompClient = Stomp.over(socket);
const from = "name";

let messageArray = []

stompClient.connect({}, function (frame) {
    console.log('Stomp client connected');
    stompClient.subscribe(`/topic/game/${sessionId}`, function (result) {
        console.log("Game move:", result.body);
    });
    stompClient.subscribe(`/topic/chat/${sessionId}`, function (result) {
        console.log("Game chat:", result.body);
    })
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

function sendChatMessage() {
    const message = {
        from: from,
        content: "You loser",
        time: new Date().getTime(),
    }

    const messageJson = JSON.stringify(message);

    stompClient.send('/app/game/' + sessionId + '/chat', {}, messageJson);
}

// if "from" == httpsession then print "You: " else "Name:"