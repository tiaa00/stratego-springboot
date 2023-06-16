let sessionId;
let playerName;
var socket = new SockJS('/gs-guide-websocket');
var stompClient = Stomp.over(socket);

async function init() {
    const request1 = await fetch('/roomId');
    sessionId = await request1.text();
    
    const request2 = await fetch('/name');
    playerName = await request2.text();
    
    stompClient.connect({}, function (frame) {
        console.log('Stomp client connected');
        stompClient.subscribe(`/topic/game/${sessionId}`, function (result) {
            console.log("Game move:", result.body);
        });
        stompClient.subscribe(`/topic/chat/${sessionId}`, function (result) {
            // Assuming result.body is a JSON string that represents a chat message object
            const messageArray = JSON.parse(result.body);
            const chatMessage = messageArray[messageArray.length - 1];
            console.log("Chat message:", chatMessage);
            
            // Create a new div for the message
            const messageDiv = document.createElement('div');
            messageDiv.classList.add('message');
            if (playerName === chatMessage.from) {
                messageDiv.classList.add('sent');
            } else {
                messageDiv.classList.add('received');
            }
            
            // Create the message content div and append it to the message div
            const messageContentDiv = document.createElement('div');
            messageContentDiv.classList.add('message-content');
            messageDiv.appendChild(messageContentDiv);
            
            // Create a p element for the message text and append it to the message content div
            const messageText = document.createElement('p');
            messageText.innerText = chatMessage.content;
            messageContentDiv.appendChild(messageText);
            
            // Create a span element for the message time and append it to the message content div
            const messageTime = document.createElement('span');
            messageTime.classList.add('message-time');
            // Convert miliseconds to seconds
            // const time = Integer.parseInt(chatMessage.time);
            messageTime.innerText = chatMessage.time;
            messageContentDiv.appendChild(messageTime);
            
            // Append the message div to the message list
            const messageList = document.querySelector('.message-list');
            messageList.appendChild(messageDiv);
        });
        
    });
}

init()


 // Define a function to send a move message
// function sendMoveMessage() {

//   const moveMessage = {
//     sessionId: sessionId,
//     sourceX: 5,
//     sourceY: 5,
//     targetX: 6,
//     targetY: 6,
//   };

//   // Convert the move message to JSON
//   const messageJson = JSON.stringify(moveMessage);

//     stompClient.send('/app/game/' + sessionId + '/move', {}, messageJson);
// }

function sendChatMessage() {
    const input = document.getElementById('message-input');
    const content = input.value;

    const message = {
        from: playerName,
        content: content,
        time: new Date().getTime(),
    }

    const messageJson = JSON.stringify(message);

    stompClient.send('/app/game/' + sessionId + '/chat', {}, messageJson);

    // Clear the input field after sending the message
    input.value = '';
}

// if "from" == httpsession then print "You: " else "Name:"