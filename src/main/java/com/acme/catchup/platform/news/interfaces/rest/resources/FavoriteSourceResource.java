package com.acme.catchup.platform.news.interfaces.rest.resources;

/**
 * Resource record for a favorite source.
 * @summary
 * This record represents the resource for a favorite source.
 * It contains the ID, news API key, and source ID.
 * @since 1.0
 */
public record FavoriteSourceResource(Long id, String newsApiKey, String sourceId) {
}
