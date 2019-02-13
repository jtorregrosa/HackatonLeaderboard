package eu.nouss.hackatonleaderboard.repository;

import eu.nouss.hackatonleaderboard.domain.Stage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Stage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {

}
