package com.dcocos.ipldashboard.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import com.dcocos.ipldashboard.exceptions.TeamNotFoundException;
import com.dcocos.ipldashboard.model.Team;
import com.dcocos.ipldashboard.repository.TeamRepository;

@Service
public record TeamService(TeamRepository teamRepository, MatchesService matchesService) {

    public List<Team> getTeams() {
        return Streamable.of(teamRepository.findAll()).toList();
    }

    public Team getTeamByName(String teamName, int numberOfMatches) {
        var team = Optional.ofNullable(teamRepository.findByTeamName(teamName))
                .orElseThrow(() -> new TeamNotFoundException(String.format("Team %s not found.", teamName)));
        team.setMatches(matchesService.getMatches(teamName, numberOfMatches));
        return team;
    }
}
