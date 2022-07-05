package project.pawtagram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.pawtagram.entities.TypeEntity;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, Long> {
}
