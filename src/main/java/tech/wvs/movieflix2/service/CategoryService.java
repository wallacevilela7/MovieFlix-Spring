package tech.wvs.movieflix2.service;

import org.springframework.stereotype.Service;
import tech.wvs.movieflix2.controller.CategoryController;
import tech.wvs.movieflix2.controller.dto.request.CategoryRequest;
import tech.wvs.movieflix2.domain.Category;
import tech.wvs.movieflix2.repository.CategoryRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category create(CategoryRequest request) {
        var entity = new Category();
        entity.setName(request.name());

        return repository.save(entity);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }

    public Category update(Long id, CategoryRequest request) {
        // Verifica se ja existe
        //se nao existe joga exception
        var entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Category not found!"));

        //só chega aqui se existir, entao atualiza
        updateFields(request, entity);

        //retorna o objeto atualizado
        return repository.save(entity);
    }

    private static void updateFields(CategoryRequest request, Category entity) {
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