package tech.wvs.movieflix2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.wvs.movieflix2.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
