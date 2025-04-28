package tech.wvs.movieflix2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.wvs.movieflix2.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
