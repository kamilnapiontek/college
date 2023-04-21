package com.example.college;

import com.example.college.address.Address;
import com.example.college.department.Department;
import com.example.college.department.DepartmentService;
import com.example.college.enums.AcademicTitle;
import com.example.college.lecturer.Lecturer;
import com.example.college.lecturer.LecturerService;
import com.example.college.major.MajorRepository;
import com.example.college.major.MajorService;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class CollegeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegeApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CollegeRepository collegeRepository, CollegeService collegeService,
										LecturerService lecturerService, DepartmentService departmentService,
										MajorService majorService, MajorRepository majorRepository) {
		return args -> {
			College college = new College("Politechnika Częstochowska",generateFakeAddress(),500);
			collegeRepository.save(college);

			departmentService.generateDepartments(collegeService);
			departmentService.addRandomMajorsToDepartment(1,3);
			departmentService.addRandomMajorsToDepartment(1,2);
			departmentService.addRandomMajorsToDepartment(2,5);
			departmentService.addRandomMajorsToDepartment(3,2);

			majorService.addSubjectsToAllMajors();

			lecturerService.generateRandomLecturer();


		};
	}





	public static Address generateFakeAddress() {
		Faker faker = new Faker();
		return new Address(faker.address().cityName(),faker.address().streetAddress());
	}

}
