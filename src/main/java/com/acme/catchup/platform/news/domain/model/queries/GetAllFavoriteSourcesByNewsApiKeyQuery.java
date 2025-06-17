package com.acme.catchup.platform.news.domain.model.queries;

/**
 * @summary
 * This class represents the query to get all favorite sources by newsApiKey.
 * @param newsApiKey - the newsApiKey to get all favorite sources.
 */
public record GetAllFavoriteSourcesByNewsApiKeyQuery(String newsApiKey) {
    public GetAllFavoriteSourcesByNewsApiKeyQuery {
        if (newsApiKey == null || newsApiKey.isBlank())
            throw new IllegalArgumentException("newsApiKey cannot be null or empty");

    }
}

