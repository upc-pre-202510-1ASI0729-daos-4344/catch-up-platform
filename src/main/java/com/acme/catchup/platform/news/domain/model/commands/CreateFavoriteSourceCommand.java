/**
 * CreateFavoriteSourceCommand
 * @summary
 * CreateFavoriteSourceCommand is a record class that represents the command to create a favorite news source.
 */
package com.acme.catchup.platform.news.domain.model.commands;

/**
 * CreateFavoriteSourceCommand
 * @summary
 * CreateFavoriteSourceCommand is a record class that represents the command to create a favorite news source.
 */
public record CreateFavoriteSourceCommand(String newsApiKey, String sourceId) {
    /**
     * Validates the command.
     * @throws IllegalArgumentException If newsApiKey or source ID is null or empty
     */
    public CreateFavoriteSourceCommand {
        if (newsApiKey == null || newsApiKey.isBlank())
            throw new IllegalArgumentException("newsApiKey cannot be null or empty");
        if (sourceId == null || sourceId.isBlank())
            throw new IllegalArgumentException("sourceId cannot be null or empty");
    }

}
