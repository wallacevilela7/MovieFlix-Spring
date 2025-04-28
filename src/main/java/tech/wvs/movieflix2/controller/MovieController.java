package tech.wvs.movieflix2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.wvs.movieflix2.controller.dto.request.MovieRequest;
import tech.wvs.movieflix2.controller.dto.response.ApiResponse;
import tech.wvs.movieflix2.controller.dto.response.MovieResponse;
import tech.wvs.movieflix2.controller.dto.response.PaginationResponse;
import tech.wvs.movieflix2.mapper.MovieMapper;
import tech.wvs.movieflix2.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    //Create
    @PostMapping
    public ResponseEntity<MovieResponse> create(@RequestBody MovieRequest request) {
        var entity = service.create(request);

        return entity != null ?
                ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toResponse(entity)) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Find all
    @GetMapping
    public ResponseEntity<ApiResponse> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                              @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize) {

        var response = service.findAll(page, pageSize);

        return ResponseEntity.ok(new ApiResponse(
                response.getContent()
                        .stream()
                        .map(MovieMapper::toResponse)
                        .toList(),
                new PaginationResponse(
                        response.getNumber(),
                        response.getSize(),
                        response.getTotalElements(),
                        response.getTotalPages())
        ));
    }

    //Find by id
    @GetMapping(path = "/{id}")
    public ResponseEntity<MovieResponse> findyId(@PathVariable Long id) {
        var entity = service.findById(id);

        return entity.isPresent() ?
                ResponseEntity.ok(MovieMapper.toResponse(entity.get())) :
                ResponseEntity.notFound().build();
    }

    //Update
    @PutMapping(path = "/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id,
                                                @RequestBody MovieRequest request) {
        var entity = service.update(id, request);

        return entity != null ?
                ResponseEntity.ok(MovieMapper.toResponse(entity)) :
                ResponseEntity.notFound().build();
    }

    //Delete
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var deleted = service.delete(id);

        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/category/{categoryId}")
    public ResponseEntity<List<MovieResponse>> findByCategoryId(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize,
                                                                @PathVariable Long categoryId) {
        var response = service.findByCategory(page, pageSize, categoryId);

        return !response.isEmpty() ?
                ResponseEntity.ok(response.stream().map(MovieMapper::toResponse).toList()) :
                ResponseEntity.notFound().build();
    }
}
