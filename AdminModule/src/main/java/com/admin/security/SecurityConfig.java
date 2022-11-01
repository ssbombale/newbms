package com.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.admin.security.jwt.JwtFliter;
import com.admin.service.AdminImplementation;
import com.admin.service.AdminService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AdminImplementation userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public JwtFliter authenticationJwtTokenFilter() {
		return new JwtFliter();
	}

	public void configure(HttpSecurity httpSecurity) throws Exception {

//		
	 httpSecurity.csrf().disable();

//		httpSecurity.csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
//		.antMatchers("/Admin/login").permitAll().anyRequest().authenticated();

	//	httpSecurity.csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
		//		.antMatchers("/api/admin/loginctrl/**").permitAll().anyRequest().authenticated();
		
//		httpSecurity.csrf().disable()
//		// dont authenticate this particular request
//		.authorizeRequests().antMatchers("/api/admin/loginctrl/**").permitAll().
//		// all other requests need to be authenticated
//		anyRequest().authenticated();

	//	httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

	}

}