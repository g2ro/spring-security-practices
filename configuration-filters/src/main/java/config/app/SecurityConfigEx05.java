package config.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigEx05 {
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
        http
        	.formLogin((formLogin) -> {
        		formLogin.loginPage("/user/login"); // loginPage를 default에 의해 생성되는 것이 아닌 개발자가 생성한다는 뜻 // 해당 조건을 설정했다면 controller에서 해당 url를 처리할 로직을 작성야한다.
        	})
        	.authorizeHttpRequests((authorizeRequests) -> {
        		/* ACL */
        		authorizeRequests
        			.requestMatchers(new RegexRequestMatcher("^/board/?(write|delete|modify|reply).*$", null)) // ^ $ : 시작과 끝을 의미함. // ?(write|delete|modify|reply) board뒤에 올 수 있는 path 작성, 전부 없어도 괜찮
        			.authenticated()
        			
        			.anyRequest()
        			.permitAll();
        	});
    	return http.build();
    }

}
