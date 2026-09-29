package in.strikes.crudSpringBootDemo.repository;

import in.strikes.crudSpringBootDemo.entity.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface studentrepo extends JpaRepository<student, Integer> {

    Optional<student> findByIdAndDeletedFalse(int id);

    List<student> findByDeletedFalse();
}
