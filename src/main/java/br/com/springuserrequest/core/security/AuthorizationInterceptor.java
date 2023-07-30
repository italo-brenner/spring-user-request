package br.com.springuserrequest.core.security;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import br.com.springuserrequest.annotations.FunctionAnnotation;
import br.com.springuserrequest.dto.UserDTO;
import br.com.springuserrequest.enums.FunctionEnum;
import br.com.springuserrequest.enums.ProfileEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {
	
	private UserRequest userRequest;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("Pre Handler: {} {}", handler, handler.getClass());
		UserDTO userDTO = userRequest.getUser();
		log.info("Pre Handler User: {}", userDTO.getUsername());
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		log.info("Pre Handler Method: {}",method.getName());
		Annotation[] annotationList = method.getAnnotations();
		for (int i = 0; i < annotationList.length; i++) {
			Annotation annotation = annotationList[i];
			log.info("Pre Handler Annotation[{}]: {}", i, annotation.annotationType());
		}
		boolean hasFunctionAnnotation = method.isAnnotationPresent(FunctionAnnotation.class);
		log.info("Pre Handler Has FunctionAnnotation: {}", hasFunctionAnnotation);
		if (hasFunctionAnnotation) {
			FunctionAnnotation functionAnnotation = method.getAnnotation(FunctionAnnotation.class);
			FunctionEnum functionEnum = functionAnnotation.function();
			log.info("Pre Handler Functionn Enum: {}", functionEnum.name());
			ProfileEnum profileEnum = userDTO.getProfileEnum();
			if (profileEnum.getFunctionEnumMap().containsKey(functionEnum.name())) {
				log.info("Pre Handler Usuário Autorizado");
				return HandlerInterceptor.super.preHandle(request, response, handler);
			}
			log.info("Pre Handler Usuário Não Autorizado");
			throw new RuntimeException("Usuário não autorizado");
		}
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
