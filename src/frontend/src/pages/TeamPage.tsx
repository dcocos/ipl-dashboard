import React, {useEffect, useState} from 'react';
import {useParams} from "react-router";
import {MatchDetailCard} from "../components/MatchDetailCard";
import {Team} from "../models/Team";
import {MatchSmallCard} from "../components/MatchSmallCard";
import './TeamPage.scss'
import {WinLosses} from "../components/WinLosses";
import {Link} from "react-router-dom";

export const TeamPage = () => {

  const [team, setTeam] = useState<Team | null>(null);
  const {teamName} = useParams<any | undefined>();

  useEffect(
    () => {
      const fetchTeam = async () => {
        const response = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/teams/${teamName}`);
        const data = await response.json();
        setTeam(data);
      };
      fetchTeam();
    }, [teamName]
  );

  if (!team || !team.teamName) {
    return <h1>Team not found</h1>
  }
  return (
    <div className="TeamPage">
      <div className="team-name-section">
        <h1 className="team-name">{team?.teamName}</h1>
      </div>
      <div className="win-loss-section">
        <WinLosses team={team}/>
      </div>
      <div className="match-detail-section">
        <h3>Latest Matches</h3>
        <MatchDetailCard match={team?.matches[0] as any} teamName={team?.teamName}/>
      </div>
      {team?.matches.slice(1).map((match: any) =>
        <MatchSmallCard key={match.id} match={match} teamName={team?.teamName}/>
      )}
      <div className="more-link">
        <Link to={`/teams/${teamName}/matches/${process.env.REACT_APP_END_YEAR_DATE}`}>More {'>'} </Link>
      </div>
    </div>
  );
}
