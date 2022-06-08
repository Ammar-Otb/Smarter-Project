package com.example.capstoneproject.courses;

import com.example.capstoneproject.DTO.API;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController @RequestMapping("api/v1/course/")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping()
    public ResponseEntity getCourses(){
        return ResponseEntity.status(200).body(courseService.getCourses());
    }
    @PostMapping()
    public ResponseEntity addCourse(@RequestBody @Valid Course course){
        Optional<Course> course1 = Optional.of(course);
        if(course1.isEmpty()){
            return ResponseEntity.status(400).body(new API("Invalid Data Entered", 400));
        }
        courseService.addCourse(course);
        return ResponseEntity.status(201).body(new API("Successfully added "+course.getName(), 201));

    }
}
