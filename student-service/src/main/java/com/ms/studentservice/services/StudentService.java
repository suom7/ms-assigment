package com.ms.studentservice.services;

import com.ms.studentservice.repositories.StudentRepository;
import com.ms.studentservice.entities.Student;
import com.ms.studentservice.exceptions.InvalidArgumentException;
import com.ms.studentservice.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void save(Student entity) {
        if (Objects.isNull(entity)
                || Objects.isNull(entity.getEmail())
                || Objects.isNull(entity.getStudentId())
                || Objects.isNull(entity.getName())
                || Objects.isNull(entity.getPhoneNumber())) {
            throw new InvalidArgumentException("Missing mandatory fields");
        }

        this.studentRepository.save(entity);
    }

    public Student findByStudentId(String studentId) {
        return this.studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ObjectNotFoundException("Student not found"));
    }
}
