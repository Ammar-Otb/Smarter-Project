package com.example.capstoneproject.courses;

import com.example.capstoneproject.tutor.Tutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class CourseService {
    private final CoursesRepository coursesRepository;

    public List<Course> getCourses(){
        return coursesRepository.findAll();
    }

    public void addCourse(Course course){
        coursesRepository.save(course);
    }

    public Course findCourseByName(String name){
        return coursesRepository.findByName(name);
    }

    public Course findCourseById(Integer id){
        return coursesRepository.findById(id).get();
    }

    public void addTutorToSubject(Integer courseId, Tutor tutor){
        Course course = findCourseById(courseId);
        course.getTutors().add(tutor);
        coursesRepository.save(course);
    }
}
