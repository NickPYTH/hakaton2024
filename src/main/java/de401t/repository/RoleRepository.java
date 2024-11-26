package de401t.repository;

import de401t.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RoleRepository extends JpaRepository<Role, Integer> {
  Role findByCode(Integer role);

    Role findById(Long id);
}
