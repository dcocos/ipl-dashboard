import React, {useEffect, useState} from 'react';
import {MatchDetailCard} from "../components/MatchDetailCard";
import {Team} from "../models/Team";
import {MatchSmallCard} from "../components/MatchSmallCard";

export const TeamPage = () => {

  const [team, setTeam] = useState<Team | null>(null);

  useEffect(
    () => {
      const fetchMatches = async () => {
        const response = await fetch('http://localhost:8531/teams/Mumbai%20Indians');
        const data = await response.json();
        setTeam(data);
      };
      fetchMatches();
    }, []
  );

  return (
    <div className="TeamPage">
      <h1>{team?.teamName}</h1>
      <MatchDetailCard match={team?.matches[0] as any}/>
      {team?.matches.slice(1).map((match: any) =>
        <MatchSmallCard match={match}/>
      )}
    </div>
  );
}
