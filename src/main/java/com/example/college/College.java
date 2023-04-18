package com.example.college;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "college")
@Table(name = "colleges")
@Getter
@Setter
@NoArgsConstructor
public class College {
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

    @Column(
            name = "pricePerECTS",
            nullable = false
    )
    private Integer pricePerECTS;

    public College(String name, Address address, Integer pricePerECTS) {
        this.name = name;
        this.address = address;
        this.pricePerECTS = pricePerECTS;
    }
@OneToMany(
        mappedBy = "college",
        orphanRemoval = true,
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        fetch = FetchType.EAGER
)
    private List<Department> departments = new ArrayList<>();
}