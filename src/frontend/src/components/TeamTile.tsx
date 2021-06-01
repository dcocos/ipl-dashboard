import React from 'react';

import './TeamTile.scss'
import {Link} from "react-router-dom";

type Params = {
  teamName: string
}

export const TeamTile = ({teamName}: Params) => {
  return (
    <div className="TeamTile">
      <h1>
        <Link to={`/teams/${teamName}`}>{teamName}</Link>
      </h1>
    </div>
  )
}
