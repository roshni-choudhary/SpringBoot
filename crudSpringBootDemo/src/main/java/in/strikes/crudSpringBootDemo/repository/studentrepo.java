package in.strikes.crudSpringBootDemo.repository;

import in.strikes.crudSpringBootDemo.entity.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface studentrepo extends JpaRepository<student, Integer> {

}
