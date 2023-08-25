package com.clothify.fairyland;

import com.clothify.fairyland.repository.UsersRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;


@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UsersRepository.class)
public class FairylandApplication {

	public static void main(String[] args) {
		SpringApplication.run(FairylandApplication.class, args);
	}


}
