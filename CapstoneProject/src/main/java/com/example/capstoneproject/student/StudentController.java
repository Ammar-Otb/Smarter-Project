package com.example.capstoneproject.student;

import com.example.capstoneproject.DTO.API;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/student/")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity getStudents(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }
    @PostMapping("register")
    public ResponseEntity addStudent(@RequestBody @Valid Student student){
        Optional<Student> student1 = Optional.of(student);
        if(student1.isPresent()){
            studentService.registerStudent(student);
            return ResponseEntity.status(201).body(new API("Successfully Registered",201));
        }
        return ResponseEntity.status(400).body(new API("Could not register", 400));
    }

    @DeleteMapping("delete/student/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return ResponseEntity.status(201).body("Student deleted successfully!");
    }
    @PutMapping("update/email/{studentId}/{email}")
    public ResponseEntity updateEmail(@PathVariable @Email String email, @PathVariable Integer studentId){
        if(studentService.updateEmail(studentId,email))
            return ResponseEntity.status(201).body(new API("updated email",201));
        return ResponseEntity.status(400).body(new API("Invalid id", 400));
    }
}
