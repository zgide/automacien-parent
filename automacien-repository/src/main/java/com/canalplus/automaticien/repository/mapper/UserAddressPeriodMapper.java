package com.canalplus.automaticien.repository.mapper;

import com.canalplus.automaticien.repository.entities.UserAddressPeriod;

import org.springframework.stereotype.Component;

import com.canalplus.automaticien.fmk.dto.UserAddressPeriodDto;

@Component
public class UserAddressPeriodMapper implements IMapper<UserAddressPeriodDto, UserAddressPeriod> {

	@Override
	public UserAddressPeriod map(UserAddressPeriodDto dto) {
		var entity = new UserAddressPeriod();
		entity.setId(dto.getId());
		entity.setAddressId(dto.getAddressId());
		entity.setUserId(dto.getUserId());
		entity.setStartDateTime(dto.getStartDateTime());
		entity.setEndDateTime(dto.getEndDateTime());
		return entity;
	}

	@Override
	public UserAddressPeriodDto inverseMap(UserAddressPeriod entity) {
		var dto = new UserAddressPeriodDto();
		dto.setId(entity.getId());
		dto.setAddressId(entity.getAddressId());
		dto.setUserId(entity.getUserId());
		dto.setStartDateTime(entity.getStartDateTime());
		dto.setEndDateTime(entity.getEndDateTime());
		return dto;
	}

}
