import React from "react";
import {Link} from "react-router-dom";

import './YearSelector.scss';

type Params = {
  teamName: string
}

export const YearSelector = ({teamName}: Params) => {
  let years = [];
  const startYear = process.env.REACT_APP_START_YEAR_DATE as any;
  const endYear = process.env.REACT_APP_END_YEAR_DATE as any;

  for (let i = startYear; i <= endYear; i++) {
    years.push(i);
  }

  return (
    <ol className="YearSelector">
      {years.map(year => (
        <li key={year}>
          <Link to={`/teams/${teamName}/matches/${year}`}>{year}</Link>
        </li>
      ))}
    </ol>
  )
}
