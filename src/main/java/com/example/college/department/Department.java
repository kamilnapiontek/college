package com.example.college.department;

import com.example.college.College;
import com.example.college.address.Address;
import com.example.college.major.Major;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private long id;
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Embedded
    private Address address;

    public Department(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @ManyToOne
    @JoinColumn(
            name = "college_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "college_fk"
            )
    )
    private College college;

    @OneToMany(
            mappedBy = "department",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.EAGER
    )
    private List<Major> majorList;
}
