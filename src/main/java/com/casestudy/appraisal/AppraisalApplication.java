package com.casestudy.appraisal;

import com.casestudy.appraisal.dao.AppDAO;
import com.casestudy.appraisal.entity.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class AppraisalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppraisalApplication.class, args);
	}
}
