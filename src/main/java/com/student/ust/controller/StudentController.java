package com.student.ust.controller;
import com.student.ust.dto.StudentDto;
import com.student.ust.entity.Student;
import com.student.ust.exception.BussinessException;
import com.student.ust.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Student controller.
 */
@RestController
public class StudentController {
    /**
     * The Student service.
     */
    @Autowired
    StudentService studentService;

    /**
     * Get response entity.
     *
     * @param student the student
     * @return the response entity
     */
    @PutMapping("/student")
        public void updateStudent(@RequestBody Student student){
        studentService.updateStudent(student);
    }

    /**
     * Get response entity.
     *
     * @param student_id the student id
     * @return the response entity
     */
    @GetMapping("/student/{student_id}")
    public ResponseEntity<StudentDto>
    get(@PathVariable Integer student_id) {
        try {
            Student student = studentService.getStudentByID(student_id);
            return new ResponseEntity<StudentDto>(studentService.convertToDto(student), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<StudentDto>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets request.
     *
     * @param student_id the student id
     * @return the request
     */
    @GetMapping("/students")
    public ResponseEntity<Student> getRequest(@RequestParam(name = "id") Integer student_id) {
        try {
            Student student = studentService.getStudentByID(student_id);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all response entity.
     *
     * @return the response entity
     */
    @GetMapping("/student")
    public ResponseEntity<List<Student>> getAll() {
        try {

            List<Student> studentList = studentService.getAllStudents();
            return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete.
     *
     * @param student_id the student id
     */
    @DeleteMapping("/student/{student_id}")
    public void delete(@PathVariable Integer student_id) {
        studentService.deleteStudent(student_id);
    }

    /**
     * Add response entity.
     *
     * @param student the student
     * @return the response entity
     */
    @PostMapping("/student")
    public ResponseEntity<Student> add(@RequestBody Student student) {
        //log.debug("Student details >>>"+student.getStudent_id());
        try {
            studentService.saveStudent(student);
        }
        catch (BussinessException e) {
            return new ResponseEntity<Student>(HttpStatus.PRECONDITION_FAILED);
        }
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }
}
