package com.example.nanarback;

import com.example.nanarback.nanar.Nanar;
import com.example.nanarback.nanar.NanarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class NanarBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(NanarBackApplication.class, args);
	}
	@Autowired
	private NanarRepository nanarRepository;

	@Bean
	public CommandLineRunner setUpBDD() {
		return (args) -> {
			List<Nanar> nanars = new ArrayList<>(){{
				add(new Nanar(1, "nanar1", 1, 5));
				add(new Nanar(2, "nanar2", 2, 4));
				add(new Nanar(3, "nanar3", 5, 5));
				add(new Nanar(4, "nanar4", 3, 2));
				add(new Nanar(5, "nanar5", 1, 1));
			}};
			nanarRepository.saveAll(nanars);
		};
	}
}
