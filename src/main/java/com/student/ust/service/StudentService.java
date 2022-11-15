package com.student.ust.service;
import com.student.ust.dto.StudentDto;
import com.student.ust.entity.Student;
import com.student.ust.repository.StudentRepository;
import com.student.ust.util.UstUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The type Student service.
 */
@Service
public class StudentService {
    /**
     * The Student repository.
     */
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Gets student by id.
     *
     * @param Student_id the student id
     * @return the student by id
     * @throws NoSuchElementException the no such element exception
     */
    public Student getStudentByID(Integer Student_id) throws NoSuchElementException {
        //System.out.println(studentRepository.findByNameStartingWith("s").getName());
        Student studentById=studentRepository.findById(Student_id).orElseThrow(()->new NoSuchElementException());
       // studentById.setEmail(" ");
       // studentById.setPassword(" ");
        return studentById;
    }

    /**
     * Save student student.
     *
     * @param student the student
     * @return the student Student updateStudent = studentRepository.findById(student.getStudent_id()).orElseThrow(() -> new NoSuchElementException());             updateStudent.setAge(student.getAge());             updateStudent.setName(student.getName());             updateStudent.setRoll_no(student.getRoll_no());             studentRepository.save(updateStudent);             return updateStudent;
     */
    public Student saveStudent(Student student) {
        student.setCreateDate(LocalDateTime.now());
        student.setModifyDate(LocalDateTime.now());
        String email = student.getEmail();
        String password = student.getPassword();
        int resultEmail = UstUtil.validateEmail(email);
        int resultPassword = UstUtil.validatePassword(password);
        if (resultEmail == 0 && resultPassword == 0) {
            return studentRepository.save(student);
        }
        return new ResponseEntity<Student>(student, HttpStatus.OK).getBody();
    }


    /**
     * Gets all students.
     *
     * @return the all students
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Delete student.
     *
     * @param student_id the student id
     */
    public void deleteStudent(Integer student_id) {
        studentRepository.deleteById(student_id);
    }

    /**
     * Update student student.
     *
     * @param student the student
     * @return the student
     */
    public Student updateStudent(Student student) {
        Student updateStudent = studentRepository.findById(student.getStudent_id()).orElseThrow(() -> new NoSuchElementException());
                 updateStudent.setAge(student.getAge());
                 updateStudent.setName(student.getName());
                 updateStudent.setRoll_no(student.getRoll_no());
                 studentRepository.save(updateStudent);
                 return updateStudent;
    }

    /**
     * Convert to dto student dto.
     *
     * @param student the student
     * @return the student dto
     */
    public StudentDto convertToDto(Student student){
        return modelMapper.map(student,StudentDto.class);
    }
}
