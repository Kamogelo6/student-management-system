package com.morwa.studentmanagementsystem.student;

import com.morwa.studentmanagementsystem.exception.ApiRequestException;
import com.morwa.studentmanagementsystem.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record StudentService(StudentRepository studentRepository) {

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Boolean existsEmail = studentRepository.selectExistsEmail(student.getEmail());
        if (existsEmail)
            throw new ApiRequestException(student.getEmail() + " exists, try using another email.");

        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Student with id " + id + " not found"));
        studentRepository.deleteById(id);
    }
}
