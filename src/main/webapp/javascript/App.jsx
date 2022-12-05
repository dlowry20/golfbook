
import CurrentScore, {LeaderBoardRow} from "./CurrentScore";
import React, { useState, useEffect } from 'react';
import axios from 'axios';

function App() {
    const [error, setError] = useState(null);

    const [scores, setScores] = useState([]);

    useEffect(() => {
        axios("http://localhost:8080/current_round/list_by_tournament_id/1")
            .then((response) => {
                setScores(response.data);
                setError(null);
            })
    })

    if (error) return <p>An error occurred</p>

    return (
        <table>
            <thead>
                <tr>
                    <th>Golfer</th>
                    <th>Score</th>
                    <th>Holes Played</th>
                </tr>
            </thead>
            <tbody>
                {
                    scores.map(({ userId, relationToPar, holesPlayed }) => (
                        <LeaderBoardRow
                            userId={userId}
                            relationToPar={relationToPar}
                            holesPlayed={holesPlayed}
                        />
                    ))
                }
            </tbody>
        </table>
    );
}

export default App;