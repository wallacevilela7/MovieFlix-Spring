package tech.wvs.movieflix2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.wvs.movieflix2.domain.Streaming;

public interface StreamingRepository extends JpaRepository<Streaming, Long> {
}
