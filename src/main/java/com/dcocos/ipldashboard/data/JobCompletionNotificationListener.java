package com.dcocos.ipldashboard.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import com.dcocos.ipldashboard.model.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private final EntityManager em;

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.debug("Job finished! Verify the results.");

            Map<String, Team> teamData = new HashMap<>();

            em.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
                    .getResultList()
                    .stream()
                    .map(element -> new Team((String) element[0], (long) element[1]))
                    .forEach(team -> teamData.put(team.getTeamName(), team));

            em.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class)
                    .getResultList()
                    .forEach(element -> {
                        var team = teamData.get(element[0]);
                        team.setTotalMatches(team.getTotalMatches() + (long) element[1]);
                    });

            em.createQuery("select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
                    .getResultList()
                    .forEach(e -> {
                        var team = Optional.ofNullable(teamData.get(e[0]));
                        team.ifPresent(value -> value.setTotalWins((long) e[1]));
                    });

            teamData.values().forEach(em::persist);
            teamData.values().forEach(team -> log.debug("{}", team));
        }
    }
}