package com.nd.idioticspringsecuritydemo.config;

import com.nd.idioticspringsecuritydemo.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Shishkov A.V. on 16.08.18.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Bean
	public static UserDetailsService getUserDetailsService() {

		return username -> {
			Collection<Role> roles = new HashSet<>();
			roles.add(Role.USER);
			return new User(username, getPasswordEncoder().encode(username), roles);
		};
	}

	@Bean
	public static BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/index").permitAll();
	}*/

	@Order(1)
	@Configuration
	public static class CustomerSecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.antMatcher("/customer/**").authorizeRequests().anyRequest().authenticated()

					.and()

					.formLogin()
					.loginPage("/customer/login")
					.defaultSuccessUrl("/customer/main", true)
					.permitAll();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(getUserDetailsService()).passwordEncoder(getPasswordEncoder());
		}
	}

	@Order(2)
	@Configuration
	public static class DistributorSecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.antMatcher("/distributor/**").authorizeRequests().anyRequest().authenticated()

					.and()

					.formLogin()
					.loginPage("/distributor/login")
					.defaultSuccessUrl("/distributor/main", true)
					.permitAll();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(getUserDetailsService()).passwordEncoder(getPasswordEncoder());
		}
	}

}
