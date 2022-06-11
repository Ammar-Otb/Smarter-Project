package com.example.capstoneproject.student;

import com.example.capstoneproject.courses.Course;
import com.example.capstoneproject.courses.CourseService;
import com.example.capstoneproject.session.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class StudentService implements UserDetailsService {
    private final StudentRepository studentRepository;
    private final SessionService sessionService;
    private final CourseService courseService;

    public List getStudents(){
        return studentRepository.findAll();
    }
    public void registerStudent(Student student){
        String hashedPassword=new BCryptPasswordEncoder().encode(student.getPassword());
        student.setPassword(hashedPassword);
        studentRepository.save(student);
    }
    public Student findStudent(Integer id){
        return studentRepository.findById(id).get();
    }

    public Boolean updateEmail(Integer studentId, String newEmail){
        Student student= findStudent(studentId);
        Optional<Student> student1 = Optional.of(student);
        if(student1.isPresent()){
            student.setEmail(newEmail);
            studentRepository.save(student);
            return true;
        }
        return false;
    }


    public void deleteStudent(Integer id){
        studentRepository.deleteById(id);
    }

    public List getCourses(){
        return courseService.getCourses();
    }
    public Course getCourseByName(String name){
        return courseService.findCourseByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByUsername(username);
        if(student == null) {
            throw new UsernameNotFoundException("Username not found");
        }else
            return student;
    }
}
