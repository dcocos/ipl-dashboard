package com.dcocos.ipldashboard.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dcocos.ipldashboard.model.Match;
import com.dcocos.ipldashboard.repository.MatchRepository;
import com.dcocos.ipldashboard.repository.TeamRepository;

@Service
public record MatchesService(TeamRepository teamRepository, MatchRepository matchRepository) {

    public List<Match> getMatches(String teamName, int numberOfMatches) {
        return matchRepository.getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, Pageable.ofSize(numberOfMatches));
    }

}
