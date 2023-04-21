package com.example.college.lecturer;

import com.example.college.enums.AcademicTitle;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.example.college.CollegeApplication.generateFakeAddress;

@Service
@RequiredArgsConstructor
public class LecturerService {
    public Lecturer generateRandomLecturer() {
        Faker faker = new Faker();
        return new Lecturer(faker.name().firstName(),faker.name().lastName(),generateFakeAddress(),
                new BigDecimal(3000), LocalDate.now(), true, AcademicTitle.DOCTOR);
    }
}
