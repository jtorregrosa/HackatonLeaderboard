package eu.nouss.hackatonleaderboard.repository;

import eu.nouss.hackatonleaderboard.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
