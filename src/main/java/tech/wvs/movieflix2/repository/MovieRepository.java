package tech.wvs.movieflix2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.wvs.movieflix2.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
