package com.clothify.fairyland;

import com.clothify.fairyland.repository.UsersRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UsersRepository.class)
public class FairylandApplication {

	public static void main(String[] args) {
		SpringApplication.run(FairylandApplication.class, args);
	}

}
