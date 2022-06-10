package com.example.capstoneproject.student;

import com.example.capstoneproject.courses.Course;
import com.example.capstoneproject.courses.CourseService;
import com.example.capstoneproject.session.MySession;
import com.example.capstoneproject.session.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final SessionService sessionService;
    private final CourseService courseService;

    public List getStudents(){
        return studentRepository.findAll();
    }
    public void registerStudent(Student student){
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

    public Boolean addSessionToStudent(Integer studentId, Integer sessionId){
        Student student = findStudent(studentId);
        MySession session = sessionService.findSession(sessionId);
        Optional<Student> student1 = Optional.of(student);
        Optional<MySession> session1 = Optional.of(session);
        if(student1.isPresent()){
            if(session1.isPresent()){
                student.getStudentSessions().add(session);
                studentRepository.save(student);
                return true;
            }
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

}
