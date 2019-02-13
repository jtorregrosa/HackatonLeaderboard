package eu.nouss.hackatonleaderboard.service;

import eu.nouss.hackatonleaderboard.domain.Leaderboard;
/**
 * Service Interface for managing Score.
 */
public interface LeaderboardService {

    /**
     * Get the Leaderboard.
     *
     * @return the leaderboard
     */
    Leaderboard findAll();
}
