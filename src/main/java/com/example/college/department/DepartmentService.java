package com.example.college.department;

import com.example.college.CollegeService;
import com.example.college.major.Major;
import com.example.college.major.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static com.example.college.CollegeApplication.generateFakeAddress;
import static com.example.college.randomhelper.RandomHelper.generateRandomNumberFromRange;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final MajorRepository majorRepository;

    public void generateDepartments(CollegeService collegeService) {
        collegeService.addDepartment(1, new Department("Nauk ścisłych", generateFakeAddress()));
        collegeService.addDepartment(1, new Department("Nauk humanistycznych", generateFakeAddress()));
        collegeService.addDepartment(1, new Department("Angielskiego", generateFakeAddress()));
    }

    public void addRandomMajorsToDepartment(long departmentId, int howMany) {
        departmentRepository.findById(departmentId).ifPresentOrElse(department -> {
            for (int i = 0; i < howMany; i++) {
                addMajorToDepartment(department);
            }
        }, () -> {
            throw new NoSuchElementException("Department not found");
        });
    }

    private void addMajorToDepartment(Department department) {
        Major major = new Major("Kierunek " + generateRandomNumberFromRange(25, 2500), department);
        department.getMajorList().add(major);
        majorRepository.save(major);
        departmentRepository.save(department);
    }
}
