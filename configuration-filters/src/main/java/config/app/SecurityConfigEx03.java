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
public class SecurityConfigEx03 {
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
        	.formLogin((formLogin) -> {})
        	.httpBasic((httpBasic) -> {})
        	.authorizeHttpRequests((authorizeRequests) -> {
        		/* Access Control List(ACL) controller에서 어노테이션을 달던 행위를 대체해줄 수 있는 기술 */
        		authorizeRequests
        			.anyRequest().permitAll(); // 모두 통과 시키는 행위 -> 상단에서 조건들을 부여하여 해당 조건들이 모두 통과 될때 현재 코드 실행 되도록 작성  // ACL 마지막 코드에 항상 존재해야한다.
        	});
    	return http.build();
    }
}
