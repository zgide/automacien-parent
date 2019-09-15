package com.canalplus.automaticien.repository.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.canalplus.automaticien.fmk.dto.AddressDto;
import com.canalplus.automaticien.repository.entities.Address;
import com.canalplus.automaticien.repository.mapper.AddressMapper;

@Repository
public class AddressDao extends AbstractRepository implements IAddressDao {

	@Autowired 
	private AddressMapper mapper;
	
	@Override
	public Long save(AddressDto dto) {
		Address entity = mapper.map(dto);
		return save(entity);
	}
	
	@Override
	public AddressDto findById(Long id) {
		return mapper.inverseMap(getEntityManager().find(Address.class, id));
	}

	
}
