package com.canalplus.automaticien.repository.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.canalplus.automaticien.fmk.dto.UserDto;
import com.canalplus.automaticien.repository.mapper.UserMapper;

@Repository
public class UserDao extends AbstractRepository implements IUserDao {

	@Autowired
	private UserMapper mapper;

	@Override
	public Long save(UserDto dto) {
		var entity = mapper.map(dto);
		return save(entity);
	}

}
