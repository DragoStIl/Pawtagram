package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entities.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
