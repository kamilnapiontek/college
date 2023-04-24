package com.example.college.util;

import com.example.college.CollegeService;
import com.example.college.address.Address;
import com.example.college.department.Department;
import com.example.college.department.DepartmentRepository;
import com.example.college.major.Major;
import com.example.college.major.MajorRepository;
import com.example.college.major.MajorService;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static com.example.college.randomhelper.RandomHelper.generateRandomNumberFromRange;

@Service
@RequiredArgsConstructor
public class Util {
    private final DepartmentRepository departmentRepository;
    private final MajorRepository majorRepository;
    private final MajorService majorService;

    public static Address generateFakeAddress() {
        Faker faker = new Faker();
        return new Address(faker.address().cityName(), faker.address().streetAddress());
    }

    public void generateDepartments(CollegeService collegeService) {
        collegeService.addDepartment(1, new Department("Nauk ścisłych", generateFakeAddress()));
        collegeService.addDepartment(1, new Department("Nauk humanistycznych", generateFakeAddress()));
        collegeService.addDepartment(1, new Department("Angielskiego", generateFakeAddress()));
    }

    public void addRandomMajorsToDepartment(long departmentId, int howMany) {
        departmentRepository.findById(departmentId).ifPresentOrElse(
                department -> IntStream.range(0, howMany).forEach(i -> addMajorToDepartment(department)),
                () -> {
                    throw new NoSuchElementException("Department not found");
                }
        );
    }

    private void addMajorToDepartment(Department department) {
        Major major = new Major("Kierunek " + generateRandomNumberFromRange(25, 2500), department);
        department.getMajorList().add(major);
        majorRepository.save(major);
        departmentRepository.save(department);
    }

    public void addSubjectsToAllMajors() {
        majorRepository.findAll().forEach(major -> {
            majorService.createSubjectAddToMajor(major, "Przyrka", generateRandomNumberFromRange(1, 10));
            majorService.createSubjectAddToMajor(major, "Matma", generateRandomNumberFromRange(1, 10));
            majorService.createSubjectAddToMajor(major, "WOS", generateRandomNumberFromRange(1, 10));
        });
    }
}