package com.example.college;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CollegeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegeApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CollegeRepository collegeRepository, CollegeService collegeService) {
		return args -> {
			College college = new College("Politechnika Częstochowska",generateFakeAddress(),500);
			collegeRepository.save(college);

			collegeService.addDepartment(college,"Informatyki",generateFakeAddress());
			collegeService.addDepartment(college,"Matematyki",generateFakeAddress());
			collegeService.addDepartment(college,"Mechaniki",generateFakeAddress());

		};
	}

	private Address generateFakeAddress() {
		Faker faker = new Faker();
		return new Address(faker.address().cityName(),faker.address().streetAddress());
	}

}
