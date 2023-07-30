package br.com.springuserrequest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springuserrequest.annotations.FunctionAnnotation;
import br.com.springuserrequest.core.security.UserRequest;
import br.com.springuserrequest.enums.FunctionEnum;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("lab")
@AllArgsConstructor
public class LabController {
	
	private UserRequest userRequest;

	@GetMapping
	public String getLab() {
		return userRequest.getUser().getUsername();
	}

	@GetMapping("function1")
	@FunctionAnnotation(function = FunctionEnum.FUNCTION_1)
	public String getFuncao1() {
		return userRequest.getUser().getUsername();
	}

	@GetMapping("function2")
	@FunctionAnnotation(function = FunctionEnum.FUNCTION_2)
	public String getFuncao2() {
		return userRequest.getUser().getUsername();
	}

	@GetMapping("function3")
	@FunctionAnnotation(function = FunctionEnum.FUNCTION_3)
	public String getFuncao3() {
		return userRequest.getUser().getUsername();
	}
	
}
