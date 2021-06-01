import React, {useEffect, useState} from 'react';
import {useParams} from "react-router";
import {Match} from "../models/Match";
import {MatchDetailCard} from "../components/MatchDetailCard";

export const MatchPage = () => {

  const [matches, setMatches] = useState<Match[] | []>([]);
  const {teamName, year} = useParams<any | undefined>();

  useEffect(
    () => {
      const fetchMatches = async () => {
        const response = await fetch(`http://localhost:8531/teams/${teamName}/matches?year=${year}`);
        const data = await response.json();
        setMatches(data);
      };
      fetchMatches();
    }, [teamName]
  );


  return (
    <div className="MatchPage">
      <h1>Match Page</h1>
      {matches.map((match: any) =>
        <MatchDetailCard key={match.id} match={match} teamName={teamName}/>
      )}
    </div>
  );
}
