import {useState} from "react";
import "./App.css";

function Square({value, onSquareClick, isWinningSquare}) {
    return (
        <button className={`square ${isWinningSquare ? "winning" : ""}`} onClick={onSquareClick}>
            {value}
        </button>
    );
}

export default function Game() {
    const [history, setHistory] = useState([{squares: Array(9).fill(null), lastMove: null}]);
    const [currentMove, setCurrentMove] = useState(0);
    const [isAscending, setIsAscending] = useState(true);
    const xIsNext = currentMove % 2 === 0;
    const currentHistory = history[currentMove];
    const currentSquares = currentHistory.squares;
    const [selectedPlayer, setSelectedPlayer] = useState(null);
    const [bodyColor, setBodyColor] = useState('#ffffff'); // Add state for body color

    const handlePlay = (nextSquares, lastMove) => {
        const nextHistory = [...history.slice(0, currentMove + 1), {squares: nextSquares, lastMove}];
        setHistory(nextHistory);
        setCurrentMove(nextHistory.length - 1);
    }

    const handleSelectPlayer = (player) => {
        setSelectedPlayer(player);
        setHistory([{squares: Array(9).fill(null), lastMove: null}]);
        setCurrentMove(0);
        setIsAscending(true);
    }

    const jumpTo = (nextMove) => {
        setCurrentMove(nextMove);
    }

    const moves = history.map((step, move) => {
        let description;
        if (move > 0) {
            const row = Math.floor(step.lastMove / 3) + 1;
            const col = step.lastMove % 3 + 1;
            description = `Go to move #${move} (${row}, ${col})`;
        } else {
            description = 'Go to game start';
        }

        return (
            <li key={move}>
                {move === currentMove ? <b>{description}</b> :
                    <button onClick={() => jumpTo(move)}>{description}</button>}
            </li>
        );
    });

    if (!isAscending) {
        moves.reverse();
    }

    return (
        <div className="container" style={{backgroundColor: bodyColor}}>
            <h1 className="game-title">Tic-Toc Game !</h1>

            <div className="game">
                <div className="game-board">
                    <Board xIsNext={xIsNext} squares={currentSquares} onPlay={handlePlay}
                           selectedPlayer={selectedPlayer}/>
                </div>
                <div className="game-info">
                    {selectedPlayer ? (
                        <>
                            <button className="sort-btn" onClick={() => setIsAscending(!isAscending)}>
                                {isAscending ? "Sort Descending" : "Sort Ascending"}
                            </button>
                            <ol className="moves-btn">{moves}</ol>
                        </>
                    ) : (
                        <div className="player-selection">
                            <button onClick={() => handleSelectPlayer("X")}>X</button>
                            <p className={"orId"}>or</p>
                            <button className={"btnO"} onClick={() => handleSelectPlayer("O")}>O</button>
                        </div>

                    )}
                </div>

            </div>
            <input type="color" value={bodyColor}
                   onChange={(e) => setBodyColor(e.target.value)}/> {
            /* Add color picker */
        }Choose a color
        </div>
    );
}


function Board({xIsNext, squares, onPlay, selectedPlayer}) {
    const handleClick = (i) => {
        if (squares[i] || calculateWinner(squares).winner) {
            return;
        }
        const nextSquares = squares.slice();
        nextSquares[i] = xIsNext ? "X" : "O";
        onPlay(nextSquares, i);
    }

    const {winner, winningSquares} = calculateWinner(squares);
    let status;
    if (winner) {
        status = `Winner: ${winner}`;
    } else if (squares.every(Boolean)) {
        status = "Draw";
    } else if (selectedPlayer) {
        status = `Next player: ${xIsNext ? selectedPlayer : selectedPlayer === "X" ? "O" : "X"}`;
    } else {
        status = "Please select a player";
    }

    return (
        <>
            <div className="status">{status}</div>
            {Array.from({length: 3}, (_, i) => (
                <div className="board-row" key={i}>
                    {Array.from({length: 3}, (_, j) => (
                        <Square
                            key={3 * i + j}
                            value={squares[3 * i + j]}
                            onSquareClick={() => handleClick(3 * i + j)}
                            isWinningSquare={winningSquares.includes(3 * i + j)}
                        />
                    ))}
                </div>
            ))}
        </>
    );
}


function calculateWinner(squares) {
    const winningLines = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6]
    ];

    for (const line of winningLines) {
        const [a, b, c] = line;

        if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
            return {winner: squares[a], winningSquares: line};
        }
    }

    return {winner: null, winningSquares: []}; // Return an empty array when there's no winner
}

