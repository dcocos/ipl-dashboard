import React from 'react';
import {Match} from "../models/Match";

type Params = {
  match: Match
}

export const MatchSmallCard = ({ match }: Params) => {
  return (
    <div className="MatchSmallCard">
      <p>{match?.team1} vs {match?.team2}</p>
    </div>
  );
}
