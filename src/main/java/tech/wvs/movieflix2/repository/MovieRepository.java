package tech.wvs.movieflix2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.wvs.movieflix2.domain.Category;
import tech.wvs.movieflix2.domain.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findMovieByCategories(List<Category> categories);
}
