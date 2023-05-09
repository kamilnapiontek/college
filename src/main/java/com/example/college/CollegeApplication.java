package com.example.college;

import com.example.college.address.Address;
import com.example.college.enums.AcademicTitle;
import com.example.college.lecturer.Lecturer;
import com.example.college.lecturer.LecturerService;
import com.example.college.major.MajorRepository;
import com.example.college.major.MajorService;
import com.example.college.util.Util;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.w3c.dom.events.Event;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;

import static com.example.college.util.Util.generateFakeAddress;

@SpringBootApplication
public class CollegeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegeApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CollegeRepository collegeRepository, CollegeService collegeService,
										Util util, LecturerService lecturerService) {
		return args -> {
			College college = new College("Politechnika Częstochowska",generateFakeAddress(),500);
			collegeRepository.save(college);

			util.generateDepartments(collegeService);
			util.addRandomMajorsToDepartment(1,3);
			util.addRandomMajorsToDepartment(1,2);
			util.addRandomMajorsToDepartment(2,5);
			util.addRandomMajorsToDepartment(3,2);

			util.addSubjectsToAllMajors();

			lecturerService.createLecturer(new Lecturer("Jan", "Kowalski",
					new Address("Bydgoszcz","Fordońska"),
					new BigDecimal(3500), LocalDate.now().minusMonths(6),
					true, AcademicTitle.PHD));

			lecturerService.assignSubjectToLecturer(3,1);

		};
	}
}