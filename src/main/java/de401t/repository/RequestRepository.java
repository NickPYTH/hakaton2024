package de401t.repository;

import de401t.model.Request;
import de401t.model.Status;
import de401t.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("select e from Request e where e.status.id = :statusId")
    List<Request> findAllByStatusId(@Param("statusId") Long statusId);
    List<Request> findAllByClientOrderById(User user);
    List<Request> findAllByOrderById();
    List<Request> findAllByExecutorOrderById(User user);
    List<Request> findAllByClientAndStatusIsNotOrderById(User user, Status status);
    List<Request> findAllByExecutorAndStatusIsNotOrderById(User user, Status doneStatus);
    List<Request> findAllStatusIsNotOrderById(Status doneStatus);
    List<Request> findAllByAssistantOrderById(User user);
}
