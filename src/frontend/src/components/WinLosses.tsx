import React from 'react';

import "./MatchDetailCard.scss"
import {Pie} from "@ant-design/charts";
import {Team} from "../models/Team";

type Params = {
  team: Team
}

export const WinLosses = ({team}: Params) => {
  let ref: any;
  const data = [
    {
      type: 'wins',
      value: team.totalWins,
    },
    {
      type: 'losses',
      value: team.totalMatches - team.totalWins,
    }
  ];
  const config = {
    appendPadding: 10,
    data: data,
    angleField: 'value',
    colorField: 'type',
    radius: 0.8,
    legend: false as any,
    label: {
      type: 'inner',
      offset: '-50%',
      style: {
        fontSize: 15,
        textAlign: 'center',
      },
    },
    pieStyle: function pieStyle(_ref: any) {
      const type = _ref.type;
      if (type === 'wins') {
        return {fill: '#4da375'};
      }
      return {fill: '#a34d5d'};
    },
    state: {
      active: {
        style: {
          lineWidth: 0,
          fillOpacity: 0.65,
        },
      },
    },
    interactions: [{type: 'element-selected'}, {type: 'element-active'}],
  };

  return <Pie {...config} chartRef={(chartRef) => (ref = chartRef)}/>;
}
