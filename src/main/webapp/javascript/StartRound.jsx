import React, { useState, useEffect } from 'react';
import axios from 'axios';


function StartRound() {

    var todaysDate = new Date();
    var todaysDateString = todaysDate.toISOString().substr(0, 10);
    const [golfCourse, setCourseName] = useState("")
    const [datePlayed, setDatePlayed] = useState(todaysDateString)
    const [holesPlayed, setHolesPlayed] = useState("")
    const [score, setScores] = React.useState({
        frontNine: 0,
        backNine: 0,
        totalScore: 0
    })
    const handleSubmit = (event) => {
        // axios.post('http://localhost:8080/round_score', {
        //     courseId: 1,
        //     courseName: "River Oaks Golf Course",
        //     datePlayed: "2022-10-16",
        //     score: 0,
        //     coursePar: 71,
        //     userId: "user1"
        // }).then(function (response) {
        //     console.log(response)
        // });
        const json = JSON.stringify({
            "courseId": 1,
            "courseName": "River Oaks Golf Course",
            "datePlayed": todaysDateString,
            "score": 0,
            "coursePar": 71,
            "userId": "user1",
            "tournamentId": 1
        })
        axios.post("http://localhost:8080/round_score", json, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Basic dXNlcjE6cGFzc3dvcmQ='
            },
        }).then((response) => {
            alert(response.data.data())
        }, (error) => {
            alert(`Error: ${error}`)
        });


     }

    return (
        <form onSubmit={handleSubmit}>
            <label>Golf Course:
            <input
                type="text"
                value={golfCourse}
                onChange={(event) => setCourseName(event.target.value)}
            />
            </label>
            <label>Date:
            <input
                type="date"
                defaultValue={todaysDateString}
                onChange={(event) => setDatePlayed(event.target.value)}
            />
            </label>
            <label>Front 9
                <input
                    type="number"
                    name="frontNine"
                    value={score.frontNine}
                    onChange={handleChange}
                />
            </label>
            <label>Back 9
                <input
                    type="number"
                    name="backNine"
                    value={score.backNine}
                    onChange={handleChange}
                />
            </label>
            <label>Total Score
                <input
                    type="number"
                    name="totalScore"
                    value={score.totalScore}
                    onChange={handleChange}
                />
            </label>
            <label>Holes Played
                <input
                    type="number"
                    name="holesPlayed"
                    value={holesPlayed}
                    onChange={(event) => setHolesPlayed(event.target.value)}
                />
            </label>
            <input type="submit" />
        </form>
    )
}

function handleChange(event) {
    const value = event.target.value;
    setScores({
        [event.target.name]: value
    });
}



export default StartRound;