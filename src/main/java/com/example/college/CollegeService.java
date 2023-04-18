package com.example.college;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CollegeService {
    private final CollegeRepository collegeRepository;
    public void addDepartment(College college, String departmentName, Address departmentAddress) {
        Optional<College> collegeOptional = collegeRepository.findById(college.getId());
        collegeOptional.ifPresentOrElse(c -> {
            Department department = new Department(departmentName,departmentAddress,college);
            c.getDepartments().add(department);
            collegeRepository.save(college);
        }, () -> System.err.println("Ni ma takiego w bazie"));
    }
}
