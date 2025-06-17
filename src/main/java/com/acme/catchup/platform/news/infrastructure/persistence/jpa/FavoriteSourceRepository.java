package com.acme.catchup.platform.news.infrastructure.persistence.jpa;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA repository for FavoriteSource entity.
 * @summary
 * This interface extends JpaRepository to provide CRUD operations for FavoriteSource entity.
 * It extends Spring Data JpaRepository with FavoriteSource as the entity type and Long as the ID type.
 * @since 1.0
 */
@Repository
public interface FavoriteSourceRepository extends JpaRepository<FavoriteSource, Long> {
    /**
     * Find all favorite sources by news API key.
     * @param newsApiKey News API key
     * @return List of favorite sources
     */
    List<FavoriteSource> findAllByNewsApiKey(String newsApiKey);

    /**
     * Check if a favorite source exists by news API key and source ID.
     * @param newApiKey News API key
     * @param sourceId Source ID
     * @return True if exists, false otherwise
     */
    boolean existsByNewsApiKeyAndSourceId(String newApiKey, String sourceId);

    /**
     * Find a favorite source by news API key and source ID.
     * @param newsApiKey News API key
     * @param sourceId Source ID
     * @return Favorite source
     */
    Optional<FavoriteSource> findByNewsApiKeyAndSourceId(String newsApiKey, String sourceId);
}
