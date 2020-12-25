package com.bist.homework.todolist;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//http://localhost:8080/swagger-ui/index.html?url=/v3/api-docs&validatorUrl=#/
@OpenAPIDefinition(
		info = @Info(
				title = "TODO List API Reference",
				version = "1.0.0",
				description = "TODO List API",
				license = @License(name = "Apache 2.0"),
				contact = @Contact(name = "Ugur Murtaza", email = "ugurmurtaza@gmail.com")
		)
)
@SpringBootApplication
public class TodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
}
