package com.vskreddy652.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringBootSecurityConfig extends WebSecurityConfigurerAdapter {

	// Authentication : set user/password details and mention the role
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
				.withUser("user").password("pass").roles("USER")
				.and().withUser("admin").password("pass").roles("USER", "ADMIN")
				.and().withUser("slr").password("pass").roles("SELR")
				.and().withUser("agent").password("pass").roles("AGENT");
		
		System.out.println("Accessing");
	}

	// Authorization : mention which role can access which URL
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
		.antMatchers("/userlogin?", "/xyz").hasRole("USER")
		.antMatchers("/adminlogin").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/slrlogin").hasAnyRole("USER", "SELR", "ADMIN")
		.antMatchers("/agent/createuser").hasRole("AGENT")
		.and()
		.logout().logoutUrl("/logout").deleteCookies("remove").invalidateHttpSession(false).and().csrf()
		.disable().headers().frameOptions().disable();
		
	}
}
