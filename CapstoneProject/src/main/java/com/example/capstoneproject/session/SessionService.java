package com.example.capstoneproject.session;

import com.example.capstoneproject.report.Report;
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

    public void reportSession(Report report, Integer sessionId) throws Exception{
        MySession session = findSession(sessionId);
        session.setReport(report);
        mySessionRepository.save(session);

    }
}
