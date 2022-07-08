package com.ms.studentservice.web;

import com.ms.studentservice.entities.Student;
import com.ms.studentservice.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> create(@RequestBody Student student) {
        service.save(student);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> findByStudentId(@PathVariable String studentId) {
        return ResponseEntity.ok(this.service.findByStudentId(studentId));
    }
}
