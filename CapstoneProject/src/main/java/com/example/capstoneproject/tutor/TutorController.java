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
    public ResponseEntity addCourseToTutor(@PathVariable Integer tutorId, @PathVariable Integer courseId) {
        Tutor tutor = tutorService.getTutorById(tutorId);
        if(tutor == null){
            return ResponseEntity.status(400).body(new API("Data invalid", 400));
        }
        courseService.addTutorToSubject(courseId, tutor);
        return ResponseEntity.status(201).body(new API("Successfully added subject!!", 201));
    }

    @DeleteMapping("delete/session/{id}")
    public ResponseEntity deleteSession(@PathVariable Integer id){
        sessionService.deleteSession(id);
        return ResponseEntity.status(201).body("Successfully deleted session");

    }

    @PostMapping("report/{sessionId}")
    public ResponseEntity addReport(@RequestBody Report report, @PathVariable Integer sessionId){
        try {
            sessionService.reportSession(report, sessionId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Added report to session");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Session Id invalid");
        }
    }
}
