package com.example.capstoneproject.tutor;

import com.example.capstoneproject.Request.MyRequest;
import com.example.capstoneproject.courses.Course;
import com.example.capstoneproject.session.MySession;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity
public class Tutor implements UserDetails {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private static String role = "ROLE_TUTOR";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tutorId;

    @NotNull(message = "Name must not be null")
    @Size(min = 4, max = 25, message = "Name length should be between 4 and 25")
    @Column(unique = true)
    private String username;
    @NotNull(message = "Email must not be null") @Email @Column(unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String email;
    @NotNull(message = "Password must not be null")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must contain eight characters, at least one uppercase letter, one lowercase letter and one number")
    @Size(min = 8)
    private String password;
    @NotNull(message = "Enter the nationality")
    private String nationality;
    @NotNull(message = "Please enter the age") @Positive @Range(min = 25, max = 65, message = "Age has to be between 25 and 65")
    private Integer age;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "tutor_id",
            referencedColumnName = "tutorId"
    )
    private Set<MySession> mySessions;

    @ManyToMany(mappedBy = "tutors", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Course> courses;

    @ManyToMany(mappedBy = "tutors1", cascade = CascadeType.ALL)
//    @JsonIgnore
    private Set<MyRequest> requests;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
