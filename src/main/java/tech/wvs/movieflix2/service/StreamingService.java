package tech.wvs.movieflix2.service;

import org.springframework.stereotype.Service;
import tech.wvs.movieflix2.controller.dto.request.StreamingRequest;
import tech.wvs.movieflix2.domain.Streaming;
import tech.wvs.movieflix2.repository.StreamingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StreamingService {
    
    private final StreamingRepository repository;

    public StreamingService(StreamingRepository repository) {
        this.repository = repository;
    }

    public Streaming create(StreamingRequest request) {
        var entity = new Streaming();
        entity.setName(request.name());

        return repository.save(entity);
    }

    public List<Streaming> findAll() {
        return repository.findAll();
    }

    public Optional<Streaming> findById(Long id) {
        return repository.findById(id);
    }

    public Streaming update(Long id, StreamingRequest request) {
        var entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Streaming not found!"));

        updateFields(request, entity);

        return repository.save(entity);
    }

    private static void updateFields(StreamingRequest request, Streaming entity) {
        if (request.name() != null && !request.name().isBlank()) {
            entity.setName(request.name());
        }
    }

    public boolean delete(Long id) {
        var exists = repository.existsById(id);

        if(exists) {
            repository.deleteById(id);
        }

        return exists;
    }
}
