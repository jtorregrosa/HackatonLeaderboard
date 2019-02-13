package eu.nouss.hackatonleaderboard.service.impl;

import eu.nouss.hackatonleaderboard.domain.Leaderboard;
import eu.nouss.hackatonleaderboard.domain.LeaderboardEntry;
import eu.nouss.hackatonleaderboard.domain.Score;
import eu.nouss.hackatonleaderboard.domain.Team;
import eu.nouss.hackatonleaderboard.repository.ScoreRepository;
import eu.nouss.hackatonleaderboard.repository.TeamRepository;
import eu.nouss.hackatonleaderboard.service.LeaderboardService;
import eu.nouss.hackatonleaderboard.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Score.
 */
@Service
@Transactional
public class LeaderboardServiceImpl implements LeaderboardService {

    private final Logger log = LoggerFactory.getLogger(LeaderboardServiceImpl.class);

    private final TeamRepository teamRepository;

    public LeaderboardServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    /**
     * Get the leaderboard.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Leaderboard findAll() {
        log.debug("Request to get all Scores");
        List<Team> teams = teamRepository.findAll();

        Leaderboard leaderboard = new Leaderboard();

        for (Team team: teams ) {
            LeaderboardEntry entry = new LeaderboardEntry();
            entry.setName(team.getName());
            for (Score score : team.getScores()) {
                entry.setValue(entry.getValue() + score.getValue());
            }

            leaderboard.addEntry(entry);
        }

        return leaderboard;
    }
}
