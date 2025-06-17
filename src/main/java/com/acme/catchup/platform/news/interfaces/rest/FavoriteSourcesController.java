package com.acme.catchup.platform.news.interfaces.rest;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.GetAllFavoriteSourcesByNewsApiKeyQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceCommandService;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceQueryService;
import com.acme.catchup.platform.news.interfaces.rest.resources.CreateFavoriteSourceResource;
import com.acme.catchup.platform.news.interfaces.rest.resources.FavoriteSourceResource;
import com.acme.catchup.platform.news.interfaces.rest.transform.CreateFavoriteSourceCommandFromResourceAssembler;
import com.acme.catchup.platform.news.interfaces.rest.transform.FavoriteSourceResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller for favorite sources.
 * @summary
 * This class provides REST endpoints for favorite sources.
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/api/v1/favorite-sources", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Favorite Sources", description = "Endpoints for favorite sources")
public class FavoriteSourcesController {
    private final FavoriteSourceCommandService favoriteSourceCommandService;
    private final FavoriteSourceQueryService favoriteSourceQueryService;

    /**
     * Constructor for FavoriteSourcesController.
     * @param favoriteSourceCommandService Favorite source command service
     * @param favoriteSourceQueryService Favorite source query service
     * @since 1.0
     * @see FavoriteSourceCommandService
     * @see FavoriteSourceQueryService
     */
    public FavoriteSourcesController(FavoriteSourceCommandService favoriteSourceCommandService, FavoriteSourceQueryService favoriteSourceQueryService) {
        this.favoriteSourceCommandService = favoriteSourceCommandService;
        this.favoriteSourceQueryService = favoriteSourceQueryService;
    }

    /**
     * Creates a favorite source.
     * @param resource CreateFavoriteSourceResource containing the news API key and source ID
     * @return ResponseEntity with the created favorite source resource, or bad request if the resource is invalid
     * @since 1.0
     * @see CreateFavoriteSourceResource
     * @see FavoriteSourceResource
     */
    @Operation(
            summary = "Create a favorite source",
            description = "Creates a favorite source with the provided news API key and source ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Favorite source created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<FavoriteSourceResource> createFavoriteSource(@RequestBody CreateFavoriteSourceResource resource) {
        Optional<FavoriteSource> favoriteSource = favoriteSourceCommandService
                .handle(CreateFavoriteSourceCommandFromResourceAssembler.toCommandFromResource(resource));
        return favoriteSource.map(source -> new ResponseEntity<>(FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Gets a favorite source by ID.
     * @param id Favorite source ID
     * @return ResponseEntity with the favorite source resource if found, or not found otherwise
     * @since 1.0
     * @see FavoriteSourceResource
     */
    @Operation(
            summary = "Get a favorite source by ID",
            description = "Gets a favorite source by the provided ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite source found"),
            @ApiResponse(responseCode = "404", description = "Favorite source not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<FavoriteSourceResource> getFavoriteSourceById(@PathVariable Long id) {
        Optional<FavoriteSource> favoriteSource = favoriteSourceQueryService.handle(new GetFavoriteSourceByIdQuery(id));
        return favoriteSource.map(source -> ResponseEntity.ok(FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Gets all favorite sources by news API key.
     * @param newsApiKey News API key
     * @return ResponseEntity with the list of favorite source resources if found, or not found otherwise
     * @since 1.0
     * @see FavoriteSourceResource
     */
    private ResponseEntity<List<FavoriteSourceResource>> getAllFavoriteSourcesByNewsApiKey(String newsApiKey) {
        var getAllFavoriteSourcesByNewsApiKeyQuery = new GetAllFavoriteSourcesByNewsApiKeyQuery(newsApiKey);
        var favoriteSources = favoriteSourceQueryService.handle(getAllFavoriteSourcesByNewsApiKeyQuery);
        if (favoriteSources.isEmpty()) return ResponseEntity.notFound().build();
        var favoriteSourceResources = favoriteSources.stream().map(FavoriteSourceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(favoriteSourceResources);
    }

    /**
     * Gets a favorite source by news API key and source ID.
     * @param newsApiKey News API key
     * @param sourceId Source ID
     * @return ResponseEntity with the favorite source resource if found, or not found otherwise
     * @since 1.0
     * @see FavoriteSourceResource
     */
    private ResponseEntity<FavoriteSourceResource> getFavoriteSourceByNewsApiKeyAndSourceId(String newsApiKey, String sourceId) {
        var getFavoriteSourceByNewsApiKeyAndSourceIdQuery = new GetFavoriteSourceByNewsApiKeyAndSourceIdQuery(newsApiKey, sourceId);
        var favoriteSource = favoriteSourceQueryService.handle(getFavoriteSourceByNewsApiKeyAndSourceIdQuery);
        if (favoriteSource.isEmpty()) return ResponseEntity.notFound().build();
        return favoriteSource.map(source -> ResponseEntity.ok(FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Gets favorite sources with parameters.
     * @summary
     * This method gets favorite sources based on the parameters provided.
     * If the parameters contain newsApiKey and sourceId, it gets the favorite source by news API key and source ID.
     * If the parameters contain only newsApiKey, it gets all favorite sources by news API key.
     * @param params Map of parameters including newsApiKey and optionally sourceId
     * @return ResponseEntity with the favorite source resource or resources according to the parameters, or bad request if the parameters are invalid
     * @since 1.0
     * @see FavoriteSourceResource
     */
    @Operation(
            summary = "Get favorite sources with parameters (News API key and optionally Source ID)",
            description = "Gets favorite sources based on the provided parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite source(s) found"),
            @ApiResponse(responseCode = "404", description = "Favorite source(s) not found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @Parameters({
            @Parameter(name = "newsApiKey", description = "News API key", required = true),
            @Parameter(name = "sourceId", description = "Source ID")})
    @GetMapping
    public ResponseEntity<?> getFavoriteSourcesWithParameters(
            @Parameter(name = "params", hidden = true)
            @RequestParam Map<String, String> params) {
        if (params.containsKey("newsApiKey") && params.containsKey("sourceId")) {
            return getFavoriteSourceByNewsApiKeyAndSourceId(params.get("newsApiKey"), params.get("sourceId"));
        } else if (params.containsKey("newsApiKey")) {
            return getAllFavoriteSourcesByNewsApiKey(params.get("newsApiKey"));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
