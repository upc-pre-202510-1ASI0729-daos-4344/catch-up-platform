package com.acme.catchup.platform.news.domain.services;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;

import java.util.Optional;

/**
 * @name FavoriteSourceCommandService
 * @summary
 * This interface represents the service to handle favorite source commands.
 */
public interface FavoriteSourceCommandService {
    /**
     * Handles the create favorite source command.
     * @param command The create favorite source command.
     * @return The created favorite source.
     *
     * @throws IllegalArgumentException If newsApiKey or source ID is null or empty
     * @see CreateFavoriteSourceCommand
     */
    Optional<FavoriteSource> handle(CreateFavoriteSourceCommand command);
}
