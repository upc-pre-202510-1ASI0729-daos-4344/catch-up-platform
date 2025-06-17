package com.acme.catchup.platform.news.interfaces.rest.transform;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.interfaces.rest.resources.FavoriteSourceResource;

/**
 * Assembler to create a FavoriteSourceResource from a FavoriteSource entity.
 * @since 1.0
 */
public class FavoriteSourceResourceFromEntityAssembler {
    /**
     * Converts a FavoriteSource entity to a FavoriteSourceResource.
     * @param entity FavoriteSource entity to convert
     * @return FavoriteSourceResource created from the entity
     */
    public static FavoriteSourceResource toResourceFromEntity(FavoriteSource entity) {
        return new FavoriteSourceResource(entity.getId(), entity.getNewsApiKey(), entity.getSourceId());
    }
}
