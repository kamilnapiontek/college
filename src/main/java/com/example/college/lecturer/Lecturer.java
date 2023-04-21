package com.example.college.lecturer;

import com.example.college.address.Address;
import com.example.college.department.Department;
import com.example.college.enums.AcademicTitle;
import com.example.college.subject.Subject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private long id;
    private String firstName;
    private String lastName;
    @Embedded
    private Address address;
    private BigDecimal salary;
    private LocalDate hireDate;
    private boolean activeEmployee;
@Enumerated(EnumType.STRING)
    private AcademicTitle academicTitle;

    public Lecturer(String firstName, String lastName, Address address, BigDecimal salary, LocalDate hireDate, boolean activeEmployee, AcademicTitle academicTitle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.salary = salary;
        this.hireDate = hireDate;
        this.activeEmployee = activeEmployee;
        this.academicTitle = academicTitle;
    }

    @ManyToMany
    @JoinTable(
            name = "intermediary table",
            joinColumns = @JoinColumn(name = "lecturer_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> currentlyTeachingSubjects;

}
