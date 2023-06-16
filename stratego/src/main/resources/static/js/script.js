// Define the available pieces and their limits
const pieceLimits = {
  playerA: {
    bomb: 6,
    marshal: 1,
    general: 1,
    colonel: 2,
    major: 3,
    captain: 4,
    liutenant: 4,
    sergeant: 4,
    miner: 5,
    scout: 8,
    spy: 1,
    flag: 1
    // Define limits for other pieces
  },
  playerB: {
    bomb: 6,
    marshal: 1,
    general: 1,
    colonel: 2,
    major: 3,
    captain: 4,
    liutenant: 4,
    sergeant: 4,
    miner: 5,
    scout: 8,
    spy: 1,
    flag: 1
    // Define limits for other pieces
  }
};

// Keep track of the remaining pieces for each player
let remainingPieces = {
  playerA: { ...pieceLimits.playerA },
  playerB: { ...pieceLimits.playerB }
};

// Get the game board container element
const gameBoardContainer = document.querySelector(".grid-container");

// Add event listener to the game board cells
gameBoardContainer.addEventListener("click", function (event) {
  // Check if the event target is a game board cell
  const clickedCell = event.target;
  const cellId = clickedCell.id;

  // Get the selected piece from the dropdown
  const selectedPiece = document.getElementById("pieceDropdown").value;

  // Get the current player
  const currentPlayer = "playerA"; // Replace with your logic to determine the current player

  // Check if the current player has reached the limit for the selected piece
  if (remainingPieces[currentPlayer][selectedPiece] <= 0) {
    // Limit reached, show an error message or prevent placement
    console.log("You have reached the limit for this piece.");
    return;
  }

  // Set the background image of the clicked cell
  clickedCell.style.backgroundImage = `url('../static/images/${selectedPiece}.jpg')`;

  // Decrement the count for the placed piece
  remainingPieces[currentPlayer][selectedPiece]--;

  // Update the remaining piece counts on the frontend if needed
  updateRemainingPieceCounts();
});

function updateRemainingPieceCounts() {
  // Example function to update the remaining piece counts on the frontend
  const remainingPieceCountsContainer = document.getElementById("remainingPieceCounts");
  remainingPieceCountsContainer.innerHTML = `
    <div>Player A:</div>
    <div>Bomb: ${remainingPieces.playerA.bomb}</div>
    <div>Marshal: ${remainingPieces.playerA.marshal}</div>
    <div>General: ${remainingPieces.playerA.general}</div>
    <div>Colonel: ${remainingPieces.playerA.colonel}</div>
    <div>Major: ${remainingPieces.playerA.major}</div>
    <div>Captain: ${remainingPieces.playerA.captain}</div>
    <div>Liutenant: ${remainingPieces.playerA.liutenant}</div>
    <div>Sergeant: ${remainingPieces.playerA.sergeant}</div>
    <div>Miner: ${remainingPieces.playerA.miner}</div>
    <div>Scout: ${remainingPieces.playerA.scout}</div>
    <div>Spy: ${remainingPieces.playerA.spy}</div>
    <div>Flag: ${remainingPieces.playerA.flag}</div>
    <!-- Display remaining counts for other pieces -->    
    <div>Player A:</div>
    <div>Bomb: ${remainingPieces.playerB.bomb}</div>
    <div>Marshal: ${remainingPieces.playerB.marshal}</div>
    <div>General: ${remainingPieces.playerB.general}</div>
    <div>Colonel: ${remainingPieces.playerB.colonel}</div>
    <div>Major: ${remainingPieces.playerB.major}</div>
    <div>Captain: ${remainingPieces.playerB.captain}</div>
    <div>Liutenant: ${remainingPieces.playerB.liutenant}</div>
    <div>Sergeant: ${remainingPieces.playerB.sergeant}</div>
    <div>Miner: ${remainingPieces.playerB.miner}</div>
    <div>Scout: ${remainingPieces.playerB.scout}</div>
    <div>Spy: ${remainingPieces.playerB.spy}</div>
    <div>Flag: ${remainingPieces.playerB.flag}</div>
  `;
}
