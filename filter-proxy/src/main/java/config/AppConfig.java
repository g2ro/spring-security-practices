package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import filter.RealFilter;
import jakarta.servlet.Filter;

@Configuration
public class AppConfig {
	
	@Bean
	public Filter realFilter() { // DelegatingFilterProxy에서 delegate할 때 realfilter로 지정했기 때문에 메서드 이름을 잘 지정해야함.
		return new RealFilter();
	}
}
