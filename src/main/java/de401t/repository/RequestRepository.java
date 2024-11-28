package de401t.repository;

import de401t.model.Request;
import de401t.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByClient(User user);

    List<Request> findAllByExecutor(User user);

    @Query("select e from Request e where e.status.id = :statusId")
    List<Request> findAllByStatusId(@Param("statusId") Long statusId);
}
