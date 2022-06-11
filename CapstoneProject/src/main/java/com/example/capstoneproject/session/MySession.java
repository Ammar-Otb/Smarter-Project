package com.example.capstoneproject.session;

import com.example.capstoneproject.report.Report;
import com.example.capstoneproject.student.Student;
import com.example.capstoneproject.tutor.Tutor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Time;

import javax.persistence.*;
import javax.swing.text.DateFormatter;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity
public class MySession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sessionId;
    private String date;
    private String time;
    @Pattern(regexp = "(?i)(Expired|Upcoming)")
    private String status;


    @ManyToOne()
    @JoinColumn(name = "tutor_id",
            referencedColumnName = "tutorId")
    @JsonIgnore
    private Tutor tutor;


    @ManyToOne()
    @JoinColumn(name = "student_id",
            referencedColumnName = "studentId")
    @JsonIgnore
    private Student student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id",
            referencedColumnName = "id"
    )
    private Report report;

}
