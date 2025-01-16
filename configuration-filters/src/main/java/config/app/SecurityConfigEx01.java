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
public class SecurityConfigEx01 {
    @Bean
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//      http.formLogin().loginPage(null).. // 옛날방식
    	
/*    	
    	http
        .formLogin(new Customizer<FormLoginConfigurer<HttpSecurity>>() {
			
			@Override
			public void customize(FormLoginConfigurer<HttpSecurity> t) {
			}
		}); // UsernamePasswordAuthenticationFilter => loginInterceptor와 유사한 방식, .formLogin(new configure)
*/
    	// UsernamePasswordAuthenticationFilter는 로그인만 하는 필터 어디에서 로그인해야하는지 정하는 필터가 아님.
    	// AuthorizationFilter에 의해서 로그인을 해야하는 위치를 정하는 필터
    	http
    		.formLogin((formLogin) -> { // 자바는 함수를 파라미터로 전달할 수 있는 1급 시민이 아님.
    			/*
    			formLogin
    				.loginPage("/user/login")
    				.usernameParameter("email")
    				.loginProcessingUrl("/");
    			// 감시해야할 url과 로그인 페이지 userName등을 설정 => 인터셉터와 유사한 활동을 함, 라이브러리이기 때문에 함수를 이용해서 등록됨.
    			*/
    		});
    	return http.build();
    }
}
