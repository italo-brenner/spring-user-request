package br.com.springuserrequest.dto;

import br.com.springuserrequest.enums.ProfileEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
	
	private String username;
	
	private ProfileEnum profileEnum;
	
}
