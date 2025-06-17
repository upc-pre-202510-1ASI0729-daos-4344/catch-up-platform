package com.acme.catchup.platform.news.domain.model.queries;

/**
 * @summary
 * This class represents the query to get a favorite source by newsApiKey and sourceId.
 * @param newsApiKey - The newsApiKey of the favorite source.
 * @param sourceId - The sourceId of the favorite source.
 */
public record GetFavoriteSourceByNewsApiKeyAndSourceIdQuery(String newsApiKey, String sourceId) {
    public GetFavoriteSourceByNewsApiKeyAndSourceIdQuery {
        if (newsApiKey == null || newsApiKey.isBlank())
            throw new IllegalArgumentException("newsApiKey cannot be null or empty");
        if (sourceId == null || sourceId.isBlank())
            throw new IllegalArgumentException("sourceId cannot be null or empty");
    }
}
