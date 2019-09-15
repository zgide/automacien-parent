package com.canalplus.automaticien.repository.dao;

import com.canalplus.automaticien.fmk.dto.AddressDto;

public interface IAddressDao extends ICrudDao<AddressDto> {

	AddressDto findById(Long id);

}
