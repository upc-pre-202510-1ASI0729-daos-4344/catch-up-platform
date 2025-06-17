package com.acme.catchup.platform.news.interfaces.rest.resources;

/**
 * Resource record for creating a favorite source.
 * @summary
 * This record represents the resource for creating a favorite source.
 * It contains the news API key and source ID.
 * @since 1.0
 */
public record CreateFavoriteSourceResource(String newsApiKey, String sourceId) {
    /**
     * Validates the resource.
     * @throws IllegalArgumentException If newsApiKey or source ID is null or empty
     */
    public CreateFavoriteSourceResource {
        if (newsApiKey == null || newsApiKey.isBlank())
            throw new IllegalArgumentException("newsApiKey cannot be null or empty");
        if (sourceId == null || sourceId.isBlank())
            throw new IllegalArgumentException("sourceId cannot be null or empty");
    }
}
