package com.example.capstoneproject.session;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class SessionService {
    private final MySessionRepository mySessionRepository;

    public List getSession(){
        return mySessionRepository.findAll();
    }
    public void createSession(MySession session){
        mySessionRepository.save(session);
    }
    public MySession findSession(Integer id){
        return mySessionRepository.findById(id).get();
    }
    public void deleteSession(Integer sessionId){
        mySessionRepository.deleteById(sessionId);
    }
}
