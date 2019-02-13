package eu.nouss.hackatonleaderboard.service;

import eu.nouss.hackatonleaderboard.domain.Score;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Score.
 */
public interface ScoreService {

    /**
     * Save a score.
     *
     * @param score the entity to save
     * @return the persisted entity
     */
    Score save(Score score);

    /**
     * Get all the scores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Score> findAll(Pageable pageable);


    /**
     * Get the "id" score.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Score> findOne(Long id);

    /**
     * Delete the "id" score.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
