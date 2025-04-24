package tech.wvs.movieflix2.mapper;

import tech.wvs.movieflix2.controller.dto.request.MovieRequest;
import tech.wvs.movieflix2.controller.dto.response.CategoryResponse;
import tech.wvs.movieflix2.controller.dto.response.MovieResponse;
import tech.wvs.movieflix2.controller.dto.response.StreamingResponse;
import tech.wvs.movieflix2.domain.Category;
import tech.wvs.movieflix2.domain.Movie;
import tech.wvs.movieflix2.domain.Streaming;

import java.util.List;
import java.util.stream.Collectors;

public class MovieMapper {

    //to entity
    public static Movie toEntity(MovieRequest request) {

        List<Category> categories = request
                .categories()
                .stream()
                .map(item -> {
                    Category category = new Category();
                    category.setId(item);
                    return category;
                })
                .toList();

        List<Streaming> streamings = request
                .streamings()
                .stream()
                .map(item -> {
                    Streaming streaming = new Streaming();
                    streaming.setId(item);
                    return streaming;
                })
                .toList();

        Movie entity = new Movie();
        entity.setTitle(request.title());
        entity.setDescription(request.description());
        entity.setReleaseDate(request.releaseDate());
        entity.setRating(request.rating());
        entity.setCategories(categories);
        entity.setStreamings(streamings);

        return entity;
    }

    //to response
    public static MovieResponse toResponse(Movie movie) {
        List<CategoryResponse> listCategories = movie
                .getCategories()
                .stream()
                .map(item -> {
                    return new CategoryResponse(item.getId(), item.getName());
                })
                .collect(Collectors.toList());

        List<StreamingResponse> listStreamings = movie
                .getStreamings()
                .stream()
                .map(item -> {
                    return new StreamingResponse(item.getId(), item.getName());
                })
                .collect(Collectors.toList());

        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getReleaseDate(),
                movie.getRating(),
                listCategories,
                listStreamings
        );
    }
}
