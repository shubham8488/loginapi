package loginapi.loginapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import loginapi.loginapi.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class MyConfig {
	
	@Autowired
	
	JWTAuthenticationEntryPoint entryPoint ; 
	
	@Autowired
	JWTAuthenticationFilter jwtfilter;
	
	@Autowired
	CustomUserDetailService cud;

	@Bean
	public BCryptPasswordEncoder pe() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChin(HttpSecurity http) throws Exception {

		//
		// http.csrf().disable(). authorizeHttpRequests()
		// .requestMatchers("/contact/**")
		// .hasRole("USER") .requestMatchers("/admin/**") .hasRole("ADMIN")
		// .anyRequest() .permitAll() .and() .formLogin();

		http.csrf().disable().cors().disable().authorizeHttpRequests((auth) -> {
			try {
				auth.requestMatchers("/generateToken").permitAll().
				requestMatchers("/signup").permitAll().
				anyRequest().authenticated().and()
						.sessionManagement((session) -> {
							try {
								session.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling().authenticationEntryPoint(entryPoint);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		//
		// http.csrf().disable().cors().disable().authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER")
		// .requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().permitAll().and().sessionManagement()
		// .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		// .and()
		// .exceptionHandling().authenticationEntryPoint(entryPoint);

		 http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	 @Bean
	 public DaoAuthenticationProvider daoAuthenticationProvider() {
	 DaoAuthenticationProvider dap=new DaoAuthenticationProvider();
	 dap.setUserDetailsService(cud);
	 dap.setPasswordEncoder(this.pe());
	 return dap;
	
	 }

}
