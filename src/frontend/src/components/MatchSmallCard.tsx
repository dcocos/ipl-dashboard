import React from 'react';
import {Match} from "../models/Match";
import {Link} from "react-router-dom";

type Params = {
  match: Match
  teamName: string
}

export const MatchSmallCard = ({match, teamName}: Params) => {
  const otherTeam = match.team1 === teamName ? match.team2 : match.team1;
  const otherTeamRoute = `/teams/${otherTeam}`;
  return (
    <div className="MatchSmallCard">
      <h3>vs <Link to={otherTeamRoute}>{otherTeam}</Link></h3>
      <p>{match.matchWinner} won by {match.resultMargin} {match.result}</p>
    </div>
  );
}
