package com.dcocos.ipldashboard.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dcocos.ipldashboard.model.Match;
import com.dcocos.ipldashboard.model.Team;
import com.dcocos.ipldashboard.services.MatchesService;
import com.dcocos.ipldashboard.services.TeamService;

@RestController
@CrossOrigin
public record TeamController(TeamService teamService, MatchesService matchesService) {

    @GetMapping("/teams")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Team> getTeams() {
        return teamService.getTeams();
    }

    @GetMapping("/teams/{teamName}")
    @ResponseStatus(HttpStatus.OK)
    public Team getTeam(@PathVariable String teamName) {
        return teamService.getTeamByName(teamName, 4);
    }

    @GetMapping("/teams/{teamName}/matches")
    @ResponseStatus(HttpStatus.OK)
    public List<Match> getMatchesForTeam(@PathVariable String teamName,
                                         @RequestParam int year) {
        return matchesService.getMatchesByYear(teamName, year);
    }
}
