package com.glsid.mabanque.securiter;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource ; 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder()).dataSource(dataSource)
		.usersByUsernameQuery("select username as principal, password as credentials , active as enabled from users where username = ?")
		.authoritiesByUsernameQuery("select username as principal , role as role from users_roles where username = ?  ")
		.rolePrefix("role_");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin().loginPage("/login").permitAll();
		http.authorizeRequests().antMatchers("/consultcompte","/index","/login","/compte","/client").hasAuthority("role_user");
		http.authorizeRequests().antMatchers("/saveOperation","/updateClient","/deleteClient","/addCompte","/confirmCompte").hasAuthority("role_admin");
		
	}
	
	
}
