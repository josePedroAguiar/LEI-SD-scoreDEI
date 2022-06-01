package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;
import com.example.data.WebUser;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	ArrayList<UserDetails> userDetailsList = new ArrayList<>();
	InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

	/*
	 * @Configuration
	 * 
	 * @Order(1)
	 * public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter
	 * {
	 * public AdminSecurityConfig () {
	 * super ();
	 * }
	 * 
	 * protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable().authorizeRequests()
	 * .antMatchers("/admin/**").hasRole("ADMIN")
	 * .anyRequest().permitAll()
	 * .and()
	 * .formLogin()
	 * .loginPage("/admin/login");
	 * }
	 * }
	 * 
	 * @Configuration
	 * 
	 * @Order(2)
	 * public static class UserSecurityConfig extends WebSecurityConfigurerAdapter {
	 * public UserSecurityConfig () {
	 * super ();
	 * }
	 */

	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/", "/home", "/signup", "/saveUser", "/style.css","/admin","/admin/home","/admin/login").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				
				.defaultSuccessUrl("/hello")
				.permitAll()
				.and()
				.logout().logoutUrl("/login?logout")
				.permitAll();
				
	}
	// }

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		manager.createUser(User.withDefaultPasswordEncoder()
		.username("admin1")
		.password("adminPass")
		.roles("ADMIN", "USER")
		.build());

		return manager;
	}

	public UserDetailsService userDetailsService(List<WebUser> users) {
		for (int i = 0; i < users.size(); i++) {
			try {
				manager.createUser(User.withDefaultPasswordEncoder()
						.username(users.get(i).getUsername())
						.password(users.get(i).getPassword())
						.roles("USER")
						.build());
			} catch (Exception e) {
				;
			}
		}
		return manager;
	}

	public UserDetailsService userDetailsService(String name, String password) {

		manager.createUser(User.withDefaultPasswordEncoder()
				.username(name)
				.password(password)
				.roles("USER")
				.build());

		return manager;
	}
}