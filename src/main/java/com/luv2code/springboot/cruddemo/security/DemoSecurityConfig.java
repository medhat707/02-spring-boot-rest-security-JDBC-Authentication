package com.luv2code.springboot.cruddemo.security;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    
    /* ---------------PREVIOUS LESSON -----------------------
     * ------- restricting access based on roles ----------
     * "/api/employees/**" means the user can access from this endpoint up to the
     * slashes behind it 
     * john can access the employee/ endpoint and the slashes after it
     * BUT he cannot do a post request as he is not a manager
     * SO any POST/PUT/DELETE requests will be denied with 401 error for john
     * 
     * --------------EXPLANATION ----------
     * 1. A HttpSecurity is similar to Spring Security's XML <http> element in the 
     * namespace configuration. 
     * It allows configuring web based security for specific http requests
     * 
     * 
     * 2. METHOD --- TO BEAK THE FUNCTION DOWN
     * LAMBDA EXCEPESSION:  parameters -> implementation
     * here the method authorizeHttpRequests takes configurer as a parameter
     * which is basically an object in HttpSecurity interface
     * and here comes the implementation by calling the requestMatchers method
     * to restric URLs
     * 
     */
    /* ---------------CURRENT LESSON -----------------------
     * adding support for JDBC .. no more hardcoded users
	/* 1. passing datasource as a paramater to the method
	 * 2. spring by default goes to the db and fetch the user table and
	 * the authoritues table to get the credentials and the roles
	 * by defining Datasource
	 * 
	 * 
	 */
    
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
		
	}
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    	
    	http.authorizeHttpRequests(configurer ->
    	configurer
    			.requestMatchers(HttpMethod.GET , "/api/employees").hasRole("EMPLOYEE")
    			.requestMatchers(HttpMethod.GET , "/api/employees/**").hasRole("EMPLOYEE")
    			.requestMatchers(HttpMethod.POST , "/api/employees").hasRole("MANAGER")
    			.requestMatchers(HttpMethod.PUT , "/api/employees").hasRole("MANAGER")
    			.requestMatchers(HttpMethod.DELETE , "/api/employees/**").hasRole("ADMIN")

    			);
    	//using basic authentication
    	http.httpBasic(Customizer.withDefaults());
    	//disable Cross site request forgery (CRCF)
    	//in general not required for stateless Rest-API like this 
        http.csrf(csrf -> csrf.disable());
    	
    	
    	return http.build();
    	
    	}
    
    
    
    
    
    
    
    
    
    
	/*
    @Bean
    public InMemoryUserDetailsManager aa() {

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }
    
    */

}












