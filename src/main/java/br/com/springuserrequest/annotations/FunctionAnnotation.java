package br.com.springuserrequest.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.springuserrequest.enums.FunctionEnum;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FunctionAnnotation {
	
	FunctionEnum function();
	
}
