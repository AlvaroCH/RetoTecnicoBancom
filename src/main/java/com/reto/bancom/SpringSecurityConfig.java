package com.reto.bancom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and().authorizeRequests()
				//.antMatchers(HttpMethod.GET, "/api/exchangeRate/**").hasRole("USER")
				.antMatchers(HttpMethod.GET, "/api/exchangeRate/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/exchangeRate/**").hasRole("USER")
				//.antMatchers(HttpMethod.POST, "/api/exchangeRate/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/exchangeRate/**").hasRole("ADMIN")
				.and().csrf().disable()
				.formLogin().disable();

		http.headers().frameOptions().disable();
	}

}
