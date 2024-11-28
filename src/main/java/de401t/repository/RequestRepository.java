package de401t.repository;

import de401t.model.Request;
import de401t.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByClientOrderById(User user);
    List<Request> findAllByOrderById();
    List<Request> findAllByExecutorOrderById(User user);
}
