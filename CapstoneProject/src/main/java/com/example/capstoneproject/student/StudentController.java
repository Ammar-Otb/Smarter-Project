package com.example.capstoneproject.student;

import com.example.capstoneproject.DTO.API;
import com.example.capstoneproject.courses.Course;
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
    @GetMapping("courses")
    public ResponseEntity getCourses(){
        return ResponseEntity.status(200).body(studentService.getCourses());
    }
    @GetMapping("courses/{name}")
    public ResponseEntity getCourses(@PathVariable String name){
       Course course = studentService.getCourseByName(name);
        if(course != null){
            return ResponseEntity.status(200).body(studentService.getCourseByName(name));
        }
        return ResponseEntity.status(400).body(new API("Could not find course with the name: " + name, 400));
    }

    @PostMapping("register")
    public ResponseEntity addStudent(@RequestBody @Valid Student student) throws Exception{
        try{
            studentService.registerStudent(student);
            return ResponseEntity.status(201).body(new API("Successfully Registered Student",201));
        }catch (Exception e){
            return ResponseEntity.status(400).body(new API("Could not register, check your data again.", 400));
        }
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
