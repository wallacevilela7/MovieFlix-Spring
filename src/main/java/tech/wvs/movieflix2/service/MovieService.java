package tech.wvs.movieflix2.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.jpa.persistenceunit.PersistenceManagedTypes;
import org.springframework.stereotype.Service;
import tech.wvs.movieflix2.controller.dto.request.MovieRequest;
import tech.wvs.movieflix2.domain.Category;
import tech.wvs.movieflix2.domain.Movie;
import tech.wvs.movieflix2.domain.Streaming;
import tech.wvs.movieflix2.mapper.MovieMapper;
import tech.wvs.movieflix2.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository repository;

    private final CategoryService categoryService;
    private final StreamingService streamingService;
    private final PersistenceManagedTypes persistenceManagedTypes;

    public MovieService(MovieRepository movieRepository, CategoryService categoryService, StreamingService streamingService, PersistenceManagedTypes persistenceManagedTypes) {
        this.repository = movieRepository;
        this.categoryService = categoryService;
        this.streamingService = streamingService;
        this.persistenceManagedTypes = persistenceManagedTypes;
    }

    public Movie create(MovieRequest request) {
        //1. Recebe o request e mapeia pra entidade
        Movie entity = MovieMapper.toEntity(request);

        //4. Adiciona as categorias e streamings na entidade
        entity.setCategories(findCategories(request));
        entity.setStreamings(findStreamings(request));

        //5. Salva a entidade no banco de dados
        return repository.save(entity);
    }

    public Page<Movie> findAll(Integer page, Integer pageSize) {

        //monta objeto de page
        var pageRequest = PageRequest.of(page, pageSize);

        return repository.findAll(pageRequest);
    }

    public Optional<Movie> findById(Long id) {
        return repository.findById(id);
    }

    public Movie update(Long id, MovieRequest request) {

        //1. Verificar se o filme existe com o id (trocar a exception pra um tipo especifico)
        var entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        //2. Verificar os campos que vieram com algum valor no request
        updateFields(request, entity);

        //5. Salvar atualizado os campos que vieram com algum valor no request
        return repository.save(entity);
    }

    public boolean delete(Long id) {
        var exists = repository.existsById(id);

        if (exists) {
            repository.deleteById(id);
        }

        return exists;
    }

    private List<Category> findCategories(MovieRequest request) {
        List<Category> categories = new ArrayList<>();

        request.categories().forEach(id -> {
            categoryService.findById(id).ifPresent(item -> categories.add(item));
        });

        return categories;
    }

    private List<Streaming> findStreamings(MovieRequest request) {
        List<Streaming> streamings = new ArrayList<>();

        request.streamings().forEach(id -> {
            streamingService.findById(id).ifPresent(item -> streamings.add(item));
        });

        return streamings;
    }

    private void updateFields(MovieRequest request, Movie entity) {
        if (request.title() != null && !request.title().isBlank()) {
            entity.setTitle(request.title());
        }

        if (request.description() != null && !request.description().isBlank()) {
            entity.setDescription(request.description());
        }

        if (request.rating() != null) {
            entity.setRating(request.rating());
        }

        if (request.releaseDate() != null) {
            entity.setReleaseDate(request.releaseDate());
        }

        //3. Verificar se as categorias vieram com algum valor no request
        if (request.categories() != null && !request.categories().isEmpty()) {
            List<Category> categories = findCategories(request);
            entity.setCategories(categories);
        }

        //4. Verificar se os streamings vieram com algum valor no request
        if (request.streamings() != null && !request.streamings().isEmpty()) {
            List<Streaming> streamings = findStreamings(request);
            entity.setStreamings(streamings);
        }
    }
}
