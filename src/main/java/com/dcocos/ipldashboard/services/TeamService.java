package com.dcocos.ipldashboard.services;

import java.util.List;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import com.dcocos.ipldashboard.model.Team;
import com.dcocos.ipldashboard.repository.TeamRepository;

@Service
public record TeamService(TeamRepository teamRepository, MatchesService matchesService) {

    public List<Team> getTeams() {
        return Streamable.of(teamRepository.findAll()).toList();
    }

    public Team getTeamByName(String teamName, int numberOfMatches) {
        var team = teamRepository.findByTeamName(teamName);
        team.setMatches(matchesService.getMatches(teamName, numberOfMatches));
        return team;
    }
}
