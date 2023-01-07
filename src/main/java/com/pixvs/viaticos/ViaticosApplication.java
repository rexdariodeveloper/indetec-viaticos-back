package com.pixvs.viaticos;

import com.pixvs.viaticos.config.JwtFilter;
import com.pixvs.viaticos.config.JwtFilterSAACG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {JacksonAutoConfiguration.class})
@EnableAsync
@EnableScheduling
public class ViaticosApplication {

	@Autowired
	Environment environment;

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter(environment.getProperty("environments.jwt.secret")));
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean jwtFilterSAACG() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilterSAACG(environment.getProperty("environments.jwt.secret")));
		registrationBean.addUrlPatterns("/apisaacg/*");
		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(ViaticosApplication.class, args);
	}

}
