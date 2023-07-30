package br.com.springuserrequest.core.security;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import br.com.springuserrequest.dto.UserDTO;
import br.com.springuserrequest.repostitory.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
	
	private UserRequest userRequest;
	
	private UserRepository userRepository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("Pre Handler: {} {}", handler, handler.getClass());
		String username = request.getHeader("X-User");
		log.info("Pre Handler User: {}", username);
		UserDTO userDTO = userRepository.getUser(username);
		userRequest.authenticate(userDTO);
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("Post Handler: {} {}", handler, handler.getClass());
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("After Completion: {} {}", handler, handler.getClass());
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
