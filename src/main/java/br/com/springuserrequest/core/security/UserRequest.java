package br.com.springuserrequest.core.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import br.com.springuserrequest.dto.UserDTO;

@Component
@RequestScope
public class UserRequest {
	
	private UserDTO userDTO;

	public void authenticate(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
	public UserDTO getUser() {
		return userDTO;
	}
	
}
