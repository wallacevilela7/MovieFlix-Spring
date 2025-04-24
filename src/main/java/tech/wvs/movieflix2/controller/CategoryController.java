package tech.wvs.movieflix2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.wvs.movieflix2.controller.dto.request.CategoryRequest;
import tech.wvs.movieflix2.controller.dto.response.CategoryResponse;
import tech.wvs.movieflix2.mapper.CategoryMapper;
import tech.wvs.movieflix2.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    //Create
    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {
        var entity = service.create(request);

        return entity != null ?
                ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toResponse(entity)) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Find all
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        return ResponseEntity.ok(service.findAll()
                .stream()
                .map(item -> CategoryMapper.toResponse(item))
                .toList());
    }

    //Find by id
    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryResponse> findyId(@PathVariable Long id) {
        var entity = service.findById(id);

        return entity.isPresent() ?
                ResponseEntity.ok(CategoryMapper.toResponse(entity.get())) :
                ResponseEntity.notFound().build();
    }

    //Update
    @PutMapping(path = "/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id,
                                                   @RequestBody CategoryRequest request) {
        var entity = service.update(id, request);

        return entity != null ?
                ResponseEntity.ok(CategoryMapper.toResponse(entity)) :
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
}
