package de401t.repository;

import de401t.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
  boolean existsByUsername(String username);
  User findByUsername(String username);
  @Transactional
  void deleteByUsername(String username);

    Optional<User> findById(Long id);

    Optional<User> findByTgName(String tgName);
}
