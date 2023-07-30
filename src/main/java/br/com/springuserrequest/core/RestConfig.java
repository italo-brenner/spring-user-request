package br.com.springuserrequest.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.springuserrequest.core.security.AuthenticationInterceptor;
import br.com.springuserrequest.core.security.AuthorizationInterceptor;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class RestConfig implements WebMvcConfigurer {
	
	private AuthenticationInterceptor authenticationInterceptor;

	private AuthorizationInterceptor authorizationInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor);
		registry.addInterceptor(authorizationInterceptor);
	}
	
}
