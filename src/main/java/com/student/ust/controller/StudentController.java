package com.student.ust.controller;

import com.student.ust.entity.Student;
import com.student.ust.repository.StudentRepository;
import com.student.ust.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/student/{student_id}")
    public ResponseEntity<Student>
    get(@PathVariable Integer student_id){
        try{
            Student student=studentService.getStudentByID(student_id);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return  new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/students")
    public ResponseEntity<Student> getRequest(@RequestParam(name="id") Integer student_id){
        try{
            Student student=studentService.getStudentByID(student_id);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return  new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student")
    public ResponseEntity<List<Student>> getAll(){
        try {
            List<Student> studentList = studentService.getAllStudents();
            return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return  new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/student/{student_id}")
    public void delete(@PathVariable Integer student_id){
        studentService.deleteStudent(student_id);
    }
    @PostMapping("/student")
    public void add(@RequestBody Student student){
        studentService.saveStudent(student);
    }
    @PutMapping("/student")
    public void updateStudent(@RequestBody Student student){
        studentService.updateStudent(student);
    }
}
