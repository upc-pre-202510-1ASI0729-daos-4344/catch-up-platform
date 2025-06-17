package com.acme.catchup.platform.news.domain.services;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.GetAllFavoriteSourcesByNewsApiKeyQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * @name FavoriteSourceQueryService
 *
 * @summary
 * This interface represents the service to handle favorite source queries.
 * @since 1.0.0
 */
public interface FavoriteSourceQueryService {
    /**
     * Handles the get all favorite sources by newsApiKey query.
     * @param query The get all favorite sources by newsApiKey query.
     * @return The list of favorite sources if exists.
     * @throws IllegalArgumentException If newsApiKey is null or empty
     * @see GetAllFavoriteSourcesByNewsApiKeyQuery
     */
    List<FavoriteSource> handle(GetAllFavoriteSourcesByNewsApiKeyQuery query);
    /**
     * Handles the get favorite source by id query.
     * @param query The get favorite source by id query.
     * @return The favorite source if exists.
     * @throws IllegalArgumentException If id is null or empty
     * @see GetFavoriteSourceByIdQuery
     */
    Optional<FavoriteSource> handle(GetFavoriteSourceByIdQuery query);
    /**
     * Handles the get favorite source by newsApiKey and sourceId query.
     * @param query The get favorite source by newsApiKey and sourceId query.
     * @return The favorite source if exists.
     * @throws IllegalArgumentException If newsApiKey or source ID is null or empty
     * @see GetFavoriteSourceByNewsApiKeyAndSourceIdQuery
     */
    Optional<FavoriteSource> handle(GetFavoriteSourceByNewsApiKeyAndSourceIdQuery query);
}
