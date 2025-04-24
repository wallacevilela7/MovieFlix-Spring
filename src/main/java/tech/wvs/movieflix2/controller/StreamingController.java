package tech.wvs.movieflix2.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.wvs.movieflix2.controller.dto.request.StreamingRequest;
import tech.wvs.movieflix2.controller.dto.response.StreamingResponse;
import tech.wvs.movieflix2.mapper.StreamingMapper;
import tech.wvs.movieflix2.service.StreamingService;

import java.util.List;

@RestController
@RequestMapping("/api/streaming")
public class StreamingController {
    
    private final StreamingService service;

    public StreamingController(StreamingService service) {
        this.service = service;
    }

    //Create
    @PostMapping
    public ResponseEntity<StreamingResponse> create(@RequestBody StreamingRequest request) {
        var entity = service.create(request);

        return entity != null ?
                ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toResponse(entity)) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Find all
    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAll() {
        return ResponseEntity.ok(service.findAll()
                .stream()
                .map(item -> StreamingMapper.toResponse(item))
                .toList());
    }

    //Find by id
    @GetMapping(path = "/{id}")
    public ResponseEntity<StreamingResponse> findyId(@PathVariable Long id) {
        var entity = service.findById(id);

        return entity.isPresent() ?
                ResponseEntity.ok(StreamingMapper.toResponse(entity.get())) :
                ResponseEntity.notFound().build();
    }

    //Update
    @PutMapping(path = "/{id}")
    public ResponseEntity<StreamingResponse> update(@PathVariable Long id,
                                                   @RequestBody StreamingRequest request) {
        var entity = service.update(id, request);

        return entity != null ?
                ResponseEntity.ok(StreamingMapper.toResponse(entity)) :
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
