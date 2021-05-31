package com.dcocos.ipldashboard.resources;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dcocos.ipldashboard.model.Team;
import com.dcocos.ipldashboard.services.TeamService;

@RestController
public record TeamController(TeamService teamService) {

    @GetMapping("/teams")
    public List<Team> getTeams() {
        return teamService.getTeams();
    }

    @GetMapping("/teams/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        return teamService.getTeamByName(teamName, 4);
    }
}
