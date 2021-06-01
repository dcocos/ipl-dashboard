import React, {useEffect, useState} from 'react';
import {Team} from "../models/Team";
import './TeamPage.scss'
import {TeamTile} from "../components/TeamTile";

import './HomePage.scss'

export const HomePage = () => {

  const [teams, setTeams] = useState<Team[]>([]);

  useEffect(
    () => {
      const fetchAllTeams = async () => {
        const response = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/teams`);
        const data = await response.json();
        setTeams(data);
      };
      fetchAllTeams();
    }, []
  );

  return (
    <div className="HomePage">
      <div className="header-section">
        <h1 className="app-name">IPL Dashboard</h1>
      </div>
      <div className="team-grid">
        {teams.map((team: Team) => <TeamTile key={team.id} teamName={team.teamName}/>)}
      </div>
    </div>
  );
}
