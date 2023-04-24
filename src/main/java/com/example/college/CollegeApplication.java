package com.example.college;

import com.example.college.major.MajorRepository;
import com.example.college.major.MajorService;
import com.example.college.util.Util;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.college.util.Util.generateFakeAddress;

@SpringBootApplication
public class CollegeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegeApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CollegeRepository collegeRepository, CollegeService collegeService,
										Util util) {
		return args -> {
			College college = new College("Politechnika Częstochowska",generateFakeAddress(),500);
			collegeRepository.save(college);

			util.generateDepartments(collegeService);
			util.addRandomMajorsToDepartment(1,3);
			util.addRandomMajorsToDepartment(1,2);
			util.addRandomMajorsToDepartment(2,5);
			util.addRandomMajorsToDepartment(3,2);

			util.addSubjectsToAllMajors();
		};
	}

}