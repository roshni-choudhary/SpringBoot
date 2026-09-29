package in.strikes.crudSpringBootDemo.controller;

import in.strikes.crudSpringBootDemo.entity.student;
import in.strikes.crudSpringBootDemo.service.studentservice;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class studentcontroller {

    private studentservice studservice;

    public studentcontroller(studentservice studservice) {
        this.studservice = studservice;
    }

    //create
    @PostMapping("/create")
    public ResponseEntity<student> createsstudent(@RequestBody student stud) {
        student createdstudent = studservice.createstudent(stud);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdstudent);
    }

    //read(where deleted=0)
    @GetMapping("/get/{id}")
    public ResponseEntity<student> getStudent(@PathVariable int id) {
        student resp = studservice.getstudent(id);
        if (resp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(resp);
    }

    //readall
    @GetMapping("/getall")
    public ResponseEntity<List<student>> getStudent() {
        List<student> resp = studservice.getall();
        if (resp.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resp);
    }

    //update
    @PutMapping("/update/{id}")
    public ResponseEntity<student> updateStudent(@PathVariable int id, @RequestBody student stud) {
        student resp = studservice.updatestudent(id, stud);
        if (resp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resp);
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedstudent(@PathVariable int id) {
        Boolean isDeleted = studservice.deleteStudent(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Deleted");
    }

    //soft delete
    @PatchMapping("/delete-soft/{id}")
    public ResponseEntity<String> deletesoft(@PathVariable int id) {
     Boolean isDeleted = studservice.deletestudentsoft(id);
    if (!isDeleted) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok("Deleted");
    }
}
