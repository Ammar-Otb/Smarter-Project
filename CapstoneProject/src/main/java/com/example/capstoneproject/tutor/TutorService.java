package com.example.capstoneproject.tutor;

import com.example.capstoneproject.session.MySession;
import com.example.capstoneproject.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class TutorService  {
    private final TutorRepository tutorRepository;

    public List<Tutor> getTutors(){
        return tutorRepository.findAll();
    }

    public void addTutor(Tutor tutor){
        tutorRepository.save(tutor);
    }

    public Tutor getTutorById(Integer id){
       return tutorRepository.findById(id).get();
    }


    public Boolean createSessionWithTutorId(MySession session, Integer id) {
        Tutor t = getTutorById(id);
        Optional<Tutor> tutor = Optional.of(t);
        if(tutor.isPresent()){
            t.getMySessions().add(session);
            tutorRepository.save(t);
            return true;
        }
        return false;
    }



}
