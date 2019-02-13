package eu.nouss.hackatonleaderboard.web.rest;

import com.codahale.metrics.annotation.Timed;
import eu.nouss.hackatonleaderboard.domain.Leaderboard;
import eu.nouss.hackatonleaderboard.domain.LeaderboardEntry;
import eu.nouss.hackatonleaderboard.domain.Score;
import eu.nouss.hackatonleaderboard.domain.Team;
import eu.nouss.hackatonleaderboard.repository.TeamRepository;
import eu.nouss.hackatonleaderboard.service.LeaderboardService;
import eu.nouss.hackatonleaderboard.web.rest.errors.BadRequestAlertException;
import eu.nouss.hackatonleaderboard.web.rest.util.HeaderUtil;
import eu.nouss.hackatonleaderboard.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Team.
 */
@RestController
@RequestMapping("/api")
public class LeaderboardResource {

    private final Logger log = LoggerFactory.getLogger(LeaderboardResource.class);

    private static final String ENTITY_NAME = "team";

    private final LeaderboardService leaderboardService;

    public LeaderboardResource(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    /**
     * GET  /leaderboard : get all the leaderboard.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of leaderboard in body
     */
    @GetMapping("/leaderboard")
    @Timed
    @PermitAll
    public ResponseEntity<Leaderboard> getAllTeams() {
        log.debug("REST request to get a leaderboard");

        return ResponseEntity.ok().body(this.leaderboardService.findAll());
    }
}
