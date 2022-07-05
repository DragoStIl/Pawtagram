package project.pawtagram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.pawtagram.entities.Role;
import project.pawtagram.entities.enums.RoleCategory;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleCategory name);
}
