package com.example.college.subject;

import com.example.college.lecturer.Lecturer;
import com.example.college.major.Major;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int amountECTS;

    public Subject(String name, int amountECTS) {
        this.name = name;
        this.amountECTS = amountECTS;
    }

    @ManyToOne
    @JoinColumn(
            name = "major_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "major_id"
            )
    )
    private Major major;
    @ManyToMany(mappedBy = "currentlyTeachingSubjects")
    private List<Lecturer> currentlyTeachingLecturers;
}
