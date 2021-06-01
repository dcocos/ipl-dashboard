import {Match} from "@testing-library/react";

export type Team = {
  id: number
  teamName: string
  totalMatches: number
  totalWins: number
  matches: Array<Match>
}
