package com.dcocos.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dcocos.ipldashboard.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {

    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    @Query("" +
            "SELECT m " +
            "FROM Match m " +
            "WHERE (m.team1 = :teamName OR m.team2 = :teamName) " +
            "AND m.date BETWEEN :startDate AND :endDate " +
            "ORDER BY m.date DESC "
    )
    List<Match> getMatchesByTeamBetweenDates(String teamName, LocalDate startDate, LocalDate endDate);
}
