package com.example.capstoneproject.tutor;

import com.example.capstoneproject.Request.MyRequest;
import com.example.capstoneproject.session.MySession;
import com.example.capstoneproject.session.MySessionRepository;
import com.example.capstoneproject.student.Student;
import com.example.capstoneproject.student.StudentRepository;
import com.example.capstoneproject.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class TutorService implements UserDetailsService {
    private final TutorRepository tutorRepository;

    public List<Tutor> getTutors(){
        return tutorRepository.findAll();
    }

    public void addTutor(Tutor tutor){
        String hashedPassword=new BCryptPasswordEncoder().encode(tutor.getPassword());
        tutor.setPassword(hashedPassword);
        tutorRepository.save(tutor);
    }
    public void deleteAllTutors(){
        tutorRepository.deleteAll();
    }
    public Tutor getTutorById(Integer id){
       return tutorRepository.findById(id).get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Tutor tutor = tutorRepository.findByUsername(username);
        if(tutor == null) {
            throw new UsernameNotFoundException("Username not found");
        }else
            return tutor;
    }

//
//    public void acceptRequest(Integer requestId, Integer tutorId, MySession session) throws Exception{
//        MyRequest request = requestRepository.findById(requestId).get();
//        if(request == null || request.getTutorId() != tutorId) throw new Exception();
//        request.setStatus("Accepted");
//        createSessionWithTutorId(session, tutorId);
//        addSessionToStudent(request.getStudentId(), session.getSessionId());
//
//    }
//    public void createSessionWithTutorId(MySession session, Integer id) throws Exception {
//        Tutor t = tutorRepository.findById(id).get();
//        if(t != null){
//            t.getMySessions().add(session);
//            tutorRepository.save(t);
//        }
//        throw new Exception();
//    }
//
//    public void addSessionToStudent(Integer studentId, Integer sessionId){
//        Student student = ;
//        MySession session = sessionService.findSession(sessionId);
//        Optional<Student> student1 = Optional.of(student);
//        Optional<MySession> session1 = Optional.of(session);
//        if(student1.isPresent()){
//            if(session1.isPresent()){
//                student.getStudentSessions().add(session);
//                studentRepository.save(student);
//            }
//        }
//    }


}
