package com.student.ust.service;
import com.student.ust.entity.Student;
import com.student.ust.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public Student getStudentByID(Integer Student_id) throws NoSuchElementException {
        //System.out.println(studentRepository.findByNameStartingWith("s").getName());
        Student studentById=studentRepository.findById(Student_id).orElseThrow(()->new NoSuchElementException());
            return studentById;


    }

    public Student saveStudent(Student student) {
        student.setCreateDate(LocalDateTime.now());
        student.setModifyDate(LocalDateTime.now());
        return studentRepository.save(student);

    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public void deleteStudent(Integer student_id) {
        studentRepository.deleteById(student_id);
    }
    public Student updateStudent(Student student){
        Student updateStudent= studentRepository.findById(student.getStudent_id()).orElseThrow(()->new NoSuchElementException());
        updateStudent.setAge(student.getAge());
        updateStudent.setName(student.getName());
        updateStudent.setRoll_no(student.getRoll_no());
        studentRepository.save(updateStudent);
        return updateStudent;
    }

}
