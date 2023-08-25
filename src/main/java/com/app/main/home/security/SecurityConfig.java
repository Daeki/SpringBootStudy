package com.app.main.home.security;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.ibatis.javassist.bytecode.stackmap.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.app.main.Filters.CorsCheckFilter;

@Configuration
@EnableWebSecurity//(debug = true)
public class SecurityConfig{
	

  //  @Bean
    public CorsFilter corsFilter() {
    	/**
    	 * 작동 OK
    	 * */
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);


        return source;
    }
	
	@Bean
	WebSecurityCustomizer webSecurityConfig() {
		return web -> web
				   .ignoring()
				   .antMatchers("/images/**")
				   .antMatchers("/css/**")
				   .antMatchers("/js/**")
				   .antMatchers("/favicon/**")
				   .antMatchers("/resources/**");
		
	}
	@Bean
	SecurityFilterChain fiterChain(HttpSecurity httpSecurity)throws Exception{
		httpSecurity
		
				/**
				 * REST API에서 CSRF 토큰을 주고 받을 필요가 없으르모 해제
				*/
				.csrf()
					.disable()
				/**
				 *  cors 허용, Filter사용도 가능
				 * */
				.cors()
					//.configurationSource(this.corsConfigurationSource())
				.and()
				//.addFilterBefore(new CorsCheckFilter(), LogoutFilter.class)
				
				/**
				 * Spring Security에서 세션을 만들지 않는다. 
				 * 만약에 켜둔다면 JWT Token으로 로그인하더라도 클라이언트에서 Token값을 서버에 전달하지 않더라도 세션 값으로 로그인이 된다
				 * */
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
					
				
				

				
				.authorizeRequests()
					.anyRequest().permitAll()
					.and()
				
				;

		
		return httpSecurity.build();
					
	}
	

	
	//password를 암호화 해주는 Bean
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
