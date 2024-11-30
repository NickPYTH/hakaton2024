package de401t.repository;

import de401t.model.Group;
import de401t.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);

    User findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);

    Optional<User> findById(Long id);

    Optional<User> findByTgName(String tgName);

    User findByTgId(String tgId);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles")
    List<User> findAllByGroup(Group group);


    User findByTgId(String tgId);
}
