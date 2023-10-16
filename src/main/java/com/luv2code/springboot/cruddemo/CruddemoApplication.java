package com.luv2code.springboot.cruddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * ----------- PREVIOUS LESSON -----------------
 * we will add support for db authentication
 * 1. updating our spring boot security configuration to use JDBC
 * NOTE previous code was hard code configuration of user credentials -> delete
 * 2. what we did sofar was noop algorthm 
 * so anyone having access to db can see all user passwords
 * 3. NOW we will add support for bcrypt one way hashing algorithm
 * 4. go to any site for bcryption, generate, go back to oracle 
 * 5. modify the pw using {bcrypt} followed by the generated symbols //DONE
 * ----------- CURRENT LESSON -----------------
 * in security config

 */
@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

}
