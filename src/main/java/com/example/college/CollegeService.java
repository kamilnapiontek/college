package com.example.college;

import com.example.college.address.Address;
import com.example.college.department.Department;
import com.example.college.department.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CollegeService {
    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;
    public void addDepartment(long collegeId, Department department) {
        Optional<College> collegeOptional = collegeRepository.findById(collegeId);
        collegeOptional.ifPresentOrElse(c -> {
            department.setCollege(c);
            c.getDepartments().add(department);
            departmentRepository.save(department);
            collegeRepository.save(c);
        }, () -> {
            throw new NoSuchElementException("College not found");
        });
    }
}
