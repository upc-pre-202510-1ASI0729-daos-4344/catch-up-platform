package com.acme.catchup.platform.news.domain.model.queries;

/**
 * @summary
 * This class represents the query to get a favorite source by its id.
 * @param id - the id of the favorite source.
 */
public record GetFavoriteSourceByIdQuery(Long id) {
    public GetFavoriteSourceByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}
