package com.acme.catchup.platform.news.interfaces.rest.transform;

import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;
import com.acme.catchup.platform.news.interfaces.rest.resources.CreateFavoriteSourceResource;

/**
 * Assembler to create a CreateFavoriteSourceCommand from a CreateFavoriteSourceResource.
 * @since 1.0
 */
public class CreateFavoriteSourceCommandFromResourceAssembler {
    /**
     * Converts a CreateFavoriteSourceResource to a CreateFavoriteSourceCommand.
     * @param resource CreateFavoriteSourceResource to convert
     * @return CreateFavoriteSourceCommand created from the resource
     */
    public static CreateFavoriteSourceCommand toCommandFromResource(CreateFavoriteSourceResource resource) {
        return new CreateFavoriteSourceCommand(resource.newsApiKey(), resource.sourceId());
    }
}
