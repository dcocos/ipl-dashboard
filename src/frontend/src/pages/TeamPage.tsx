import React, {useEffect, useState} from 'react';
import {useParams} from "react-router";
import {MatchDetailCard} from "../components/MatchDetailCard";
import {Team} from "../models/Team";
import {MatchSmallCard} from "../components/MatchSmallCard";

export const TeamPage = () => {

  const [team, setTeam] = useState<Team | null>(null);
  const {teamName} = useParams<any | undefined>();

  useEffect(
    () => {
      const fetchMatches = async () => {
        const response = await fetch(`http://localhost:8531/teams/${teamName}`);
        const data = await response.json();
        setTeam(data);
      };
      fetchMatches();
    }, [teamName]
  );

  if (!team || !team.teamName) {
    return <h1>Team not found</h1>
  }
  return (
    <div className="TeamPage">
      <h1>{team?.teamName}</h1>
      <MatchDetailCard match={team?.matches[0] as any} teamName={team?.teamName}/>
      {team?.matches.slice(1).map((match: any) =>
        <MatchSmallCard key={match.id} match={match} teamName={team?.teamName}/>
      )}
    </div>
  );
}
