package com.example.capstoneproject.courses;

import com.example.capstoneproject.tutor.Tutor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    @NotNull
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_tutor",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns =@JoinColumn(
                    name = "tutor_id",
                    referencedColumnName = "tutorId")
    )
    private Set<Tutor> tutors;
}
