package com.codegym.Customer;

import com.codegym.Customer.service.CustomerService;
import com.codegym.Customer.service.ProvinceService;
import com.codegym.Customer.service.impl.CustomerServiceImpl;
import com.codegym.Customer.service.impl.ProvinceServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
	@Bean
	public CustomerService customerService(){return new CustomerServiceImpl();
	}
	@Bean
	public ProvinceService provinceService(){return new ProvinceServiceImpl();
	}
}

