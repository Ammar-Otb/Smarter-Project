package com.example.capstoneproject.Request;


import com.example.capstoneproject.student.Student;
import com.example.capstoneproject.tutor.Tutor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity
public class MyRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String courseName;

    @NotNull
    private Integer tutorId;
    @NotNull
    private Integer studentId;


    private String status = "pending";

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "requests_tutor",
            joinColumns = @JoinColumn(
                    name = "request_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns =@JoinColumn(
                    name = "tutor_id",
                    referencedColumnName = "tutorId")
    )
    @JsonIgnore
    private Set<Tutor> tutors1;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "requests_student",
            joinColumns = @JoinColumn(
                    name = "request_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns =@JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId")
    )
    @JsonIgnore

    private Set<Student> students;
}
