package br.com.springuserrequest.repostitory;

import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.springuserrequest.dto.UserDTO;
import br.com.springuserrequest.enums.ProfileEnum;

@Repository
public class UserRepository {
	
	private final Map<String, UserDTO> mapUser =
			Map.of(
					"admin", UserDTO.builder().username("admin").profileEnum(ProfileEnum.ADMIN).build(),
					"admin0", UserDTO.builder().username("admin0").profileEnum(ProfileEnum.ADMIN).build(),
					"admin1", UserDTO.builder().username("admin1").profileEnum(ProfileEnum.ADMIN).build(),
					"admin2", UserDTO.builder().username("admin2").profileEnum(ProfileEnum.ADMIN).build(),
					"admin3", UserDTO.builder().username("admin3").profileEnum(ProfileEnum.ADMIN).build(),
					"admin4", UserDTO.builder().username("admin4").profileEnum(ProfileEnum.ADMIN).build(),
					"admin5", UserDTO.builder().username("admin5").profileEnum(ProfileEnum.ADMIN).build(),
					"function1", UserDTO.builder().username("function1").profileEnum(ProfileEnum.FUNCTION_1).build(),
					"function2", UserDTO.builder().username("function2").profileEnum(ProfileEnum.FUNCTION_2).build(),
					"function3", UserDTO.builder().username("function3").profileEnum(ProfileEnum.FUNCTION_3).build());
	
	public UserDTO getUser(String username) {
		if (username != null && mapUser.containsKey(username)) {
			return mapUser.get(username);
		} else {
			return UserDTO
					.builder()
					.username("anonymousUser")
					.profileEnum(ProfileEnum.ANONYMOUS_USER)
					.build();
		}
	}
	
}
