export default function CurrentScore({
                                    userId = "SteveJobs",
                                    relationToPar = 0,
                                    holesPlayed = 0,
                                }) {
    return (
        <div className="currentScore">
            <h5 className="currentScore__userId">{userId}</h5>
            <h6 className="currentScore__relationToPar">{relationToPar}</h6>
            <p className="currentScore__holesPlayed">{holesPlayed}</p>
        </div>
    );
}

export function LeaderBoardRow({
                                         userId = "SteveJobs",
                                         relationToPar = 0,
                                         holesPlayed = 0,
                                     }) {
    return (
        <tr className="currentScore">
            <td className="currentScore__userId">{userId}</td>
            <td className="currentScore__relationToPar">{relationToPar}</td>
            <td className="currentScore__holesPlayed">{holesPlayed}</td>
        </tr>
    );
}
