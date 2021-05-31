package com.dcocos.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.dcocos.ipldashboard.model.Team;
import lombok.NonNull;

public interface TeamRepository extends CrudRepository<Team, Long> {

    Team findByTeamName(@NonNull String teamName);
}
