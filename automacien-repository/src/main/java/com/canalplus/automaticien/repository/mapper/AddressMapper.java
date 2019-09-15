package com.canalplus.automaticien.repository.mapper;

import com.canalplus.automaticien.repository.entities.Address;

import org.springframework.stereotype.Component;

import com.canalplus.automaticien.fmk.dto.AddressDto;

@Component
public class AddressMapper implements IMapper<AddressDto, Address> {

	@Override
	public Address map(AddressDto dto) {
		var entity = new Address();
		entity.setId(dto.getId());
		entity.setCity(dto.getCity());
		entity.setStreet(dto.getStreet());
		return entity;
	}

	@Override
	public AddressDto inverseMap(Address entity) {
		var dto = new AddressDto();
		dto.setId(entity.getId());
		dto.setCity(entity.getCity());
		dto.setStreet(entity.getStreet());
		return dto;
	}

}
