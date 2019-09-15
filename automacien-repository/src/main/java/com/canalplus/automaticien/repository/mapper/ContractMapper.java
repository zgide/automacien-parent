package com.canalplus.automaticien.repository.mapper;

import org.springframework.stereotype.Component;

import com.canalplus.automaticien.fmk.dto.ContractDto;
import com.canalplus.automaticien.repository.entities.Contract;

@Component
public class ContractMapper implements IMapper<ContractDto, Contract> {

	@Override
	public Contract map(ContractDto dto) {
		var entity = new Contract();
		entity.setId(dto.getId());
		entity.setUserId(dto.getUserId());
		entity.setAdressId(dto.getAddressId());
		return entity;
	}

	@Override
	public ContractDto inverseMap(Contract entity) {
		var dto = new ContractDto();
		dto.setId(entity.getId());
		dto.setUserId(entity.getUserId());
		dto.setAddressId(entity.getAdressId());
		return dto;
	}

}
