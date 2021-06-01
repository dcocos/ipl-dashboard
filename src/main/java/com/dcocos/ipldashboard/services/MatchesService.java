package com.dcocos.ipldashboard.services;

import java.time.LocalDate;
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

    public List<Match> getMatchesByYear(String teamName, int year) {
        var startDate = LocalDate.of(year, 1, 1);
        var endDate = LocalDate.of(year, 12, 31);

        return matchRepository.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
    }

}
