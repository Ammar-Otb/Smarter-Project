package com.example.capstoneproject.tutor;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


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



}
