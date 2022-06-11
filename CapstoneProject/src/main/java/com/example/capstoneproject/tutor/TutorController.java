package com.example.capstoneproject.tutor;

import com.example.capstoneproject.DTO.API;
import com.example.capstoneproject.Request.RequestService;
import com.example.capstoneproject.courses.CourseService;
import com.example.capstoneproject.report.Report;
import com.example.capstoneproject.session.MySession;
import com.example.capstoneproject.session.SessionService;
import com.example.capstoneproject.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController @RequestMapping("api/v1/tutor/") @RequiredArgsConstructor
public class TutorController {
    private final TutorService tutorService;
    private final CourseService courseService;
    private final SessionService sessionService;
    private final StudentService studentService;
    private final RequestService requestService;
    @GetMapping("")
    public ResponseEntity<List<Tutor>> getTutors(){
        return ResponseEntity.status(200).body(tutorService.getTutors());
    }

    @GetMapping("{id}")
    public ResponseEntity getTutorById(@PathVariable Integer id){
        Optional<Tutor> tutor = Optional.of(tutorService.getTutorById(id));
        if(tutor.isPresent()){
            return ResponseEntity.status(200).body(tutorService.getTutorById(id));
        }
        return ResponseEntity.status(400).body(new API("No user found", 400));
    }

    @PostMapping("/register")
    public ResponseEntity createTutor(@RequestBody @Valid Tutor tutor) throws Exception{

        try {
            tutorService.addTutor(tutor);
            return ResponseEntity.status(201).body(new API("Successfully registered tutor!", 201));
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(new API("Data invalid", 400));
        }

    }

    @PostMapping("course/add/{courseId}/{tutorId}")
    public ResponseEntity addCourseToTutor(@PathVariable Integer tutorId, @PathVariable Integer courseId){
        Tutor tutor = tutorService.getTutorById(tutorId);
        Optional<Tutor> tutor1 = Optional.of(tutor);
        if(tutor1.isEmpty()){
            return ResponseEntity.status(400).body(new API("Data invalid", 400));
        }
        courseService.addTutorToSubject(courseId, tutor);
        return ResponseEntity.status(201).body(new API("Successfully added subject!!", 201));
    }
//    @PostMapping("create/session/{tutorId}")
//    public ResponseEntity createSession(@RequestBody MySession session, @PathVariable Integer tutorId, @PathVariable Integer requestId) throws Exception {
//        Optional<MySession> session1 = Optional.of(session);
//        if(session1.isEmpty())
//            return ResponseEntity.status(400).body(new API("Data invalid", 400));
//        sessionService.createSession(session);
//        requestService.createSessionWithTutorId(session,tutorId);
//            return ResponseEntity.status(201).body(new API("Successfully created session!", 201));
//
////        return ResponseEntity.status(400).body(new API("Invalid Tutor id", 400));
////        try {
////            tutorService.acceptRequest(requestId,tutorId,session);
////            return ResponseEntity.status(201).body(new API("Successfully created accepted and created session!", 201));
////        }
////        catch (Exception e){
////            return ResponseEntity.status(400).body(new API("Invalid Data Entered, please recheck the data you entered", 400));
////        }
//    }

    @PostMapping("add/student/{studentId}/{sessionId}")
    public ResponseEntity addSessionToStudent(@PathVariable Integer studentId, @PathVariable Integer sessionId){
        if(studentService.addSessionToStudent(studentId,sessionId))
            return ResponseEntity.status(201).body("Successfully added student session");
        return ResponseEntity.status(400).body(new API("Invalid data", 400));
    }

    @DeleteMapping("delete/session/{id}")
    public ResponseEntity deleteSession(@PathVariable Integer id){
        sessionService.deleteSession(id);
        return ResponseEntity.status(201).body("Successfully deleted session");

    }

    @PostMapping("report/{sessionId}")
    public ResponseEntity addReport(@RequestBody Report report, @PathVariable Integer sessionId){
//        SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        try {
            sessionService.reportSession(report, sessionId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Added report to session");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Session Id invalid");
        }
    }
}
