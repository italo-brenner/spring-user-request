package br.com.springuserrequest.enums;

import java.util.Map;

import lombok.Getter;

@Getter
public enum ProfileEnum {
	
	ADMIN(
			Map.of(
					FunctionEnum.FUNCTION_1.name(), FunctionEnum.FUNCTION_1,
					FunctionEnum.FUNCTION_2.name(), FunctionEnum.FUNCTION_2,
					FunctionEnum.FUNCTION_3.name(), FunctionEnum.FUNCTION_3
					)),
	FUNCTION_1(Map.of(FunctionEnum.FUNCTION_1.name(), FunctionEnum.FUNCTION_1)),
	FUNCTION_2(Map.of(FunctionEnum.FUNCTION_2.name(), FunctionEnum.FUNCTION_2)),
	FUNCTION_3(Map.of(FunctionEnum.FUNCTION_3.name(), FunctionEnum.FUNCTION_3)),
	ANONYMOUS_USER(Map.of());

	ProfileEnum(Map<String, FunctionEnum> functionEnumMap) {
		this.functionEnumMap = functionEnumMap;
	}

	private Map<String, FunctionEnum> functionEnumMap;
	
}
