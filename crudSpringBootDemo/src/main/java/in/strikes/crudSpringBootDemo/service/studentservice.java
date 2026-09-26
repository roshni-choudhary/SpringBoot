package in.strikes.crudSpringBootDemo.service;

import in.strikes.crudSpringBootDemo.entity.student;
import in.strikes.crudSpringBootDemo.repository.studentrepo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class studentservice {

    private studentrepo studrepo;

    public studentservice(studentrepo studrepo) {
        this.studrepo = studrepo;
    }

    public student createstudent(student stud) {

        // business logic and validation

        // interact with DB
        student resp = studrepo.save(stud);

        return resp;
    }

    public student getstudent(int id) {
        Optional<student> studresp = studrepo.findById(id);

        if (studresp.isPresent()) {
            return studresp.get();
        }

        return null;
    }

    public List<student> getall() {
        List<student> resp = studrepo.findAll();
        return resp;
    }

    public student updatestudent(int id,student stud) {
        Optional<student> studexist = studrepo.findById(id);

        if (studexist.isPresent()) {
            student savestud=studexist.get();
            savestud.setId(id);
            savestud.setName(stud.getName());
            savestud.setAge(stud.getAge());
            student updatedstudent = studrepo.save(savestud);
            return updatedstudent;
        }

        return null;
    }
    public Boolean deleteStudent(int id) {
        Boolean isdeleted=studrepo.existsById(id);
        if(!isdeleted){return false;}
        studrepo.deleteById(id);
        return true;
    }
}
