package com.canalplus.automaticien.repository.mapper;

import org.springframework.stereotype.Component;

import com.canalplus.automaticien.fmk.dto.UserDto;
import com.canalplus.automaticien.repository.entities.User;

@Component
public class UserMapper implements IMapper<UserDto, User> {


	@Override
	public User map(UserDto dto) {
		var entity = new User();
		entity.setEmail(dto.getEmail());
		entity.setLastName(dto.getLastName());
		entity.setUsername(dto.getUsername());
		entity.setId(dto.getId());
		return entity;
	}

	@Override
	public UserDto inverseMap(User entity) {
		var dto = new UserDto();
		dto.setEmail(entity.getEmail());
		dto.setLastName(entity.getLastName());
		dto.setUsername(entity.getUsername());
		dto.setId(entity.getId());
		return dto;
	}

}
