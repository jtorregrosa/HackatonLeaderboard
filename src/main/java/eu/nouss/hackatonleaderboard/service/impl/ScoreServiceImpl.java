package eu.nouss.hackatonleaderboard.service.impl;

import eu.nouss.hackatonleaderboard.service.ScoreService;
import eu.nouss.hackatonleaderboard.domain.Score;
import eu.nouss.hackatonleaderboard.repository.ScoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Score.
 */
@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {

    private final Logger log = LoggerFactory.getLogger(ScoreServiceImpl.class);

    private final ScoreRepository scoreRepository;

    public ScoreServiceImpl(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    /**
     * Save a score.
     *
     * @param score the entity to save
     * @return the persisted entity
     */
    @Override
    public Score save(Score score) {
        log.debug("Request to save Score : {}", score);
        return scoreRepository.save(score);
    }

    /**
     * Get all the scores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Score> findAll(Pageable pageable) {
        log.debug("Request to get all Scores");
        return scoreRepository.findAll(pageable);
    }


    /**
     * Get one score by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Score> findOne(Long id) {
        log.debug("Request to get Score : {}", id);
        return scoreRepository.findById(id);
    }

    /**
     * Delete the score by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Score : {}", id);
        scoreRepository.deleteById(id);
    }
}
