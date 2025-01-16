package config.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigEx02 {
	
	@Bean // 필터를 거쳐가지 않을 접근들에 대해 처리하는 filter chain bean등록, 작성하지 않은 app도 많이 존재함.
	public WebSecurityCustomizer webSecurityCustomizer() {
	    return new WebSecurityCustomizer() {
	        @Override
	        public void customize(WebSecurity web) {
	            web
	                .ignoring()
	                .requestMatchers(new AntPathRequestMatcher("/assets/**"));
	        }
	    };
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Throwable{ //HttpSecurity 빌더 역할
		return http.build(); // 필터 체인 제작과 빌드 수행 // 보안의 핵심	
	}
}
