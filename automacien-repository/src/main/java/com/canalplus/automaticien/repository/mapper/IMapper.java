package com.canalplus.automaticien.repository.mapper;

import com.canalplus.automaticien.repository.entities.IdentifiedEntity;
import com.canalplus.automaticien.fmk.dto.IdentifiedDto;

public interface IMapper<D extends IdentifiedDto, E extends IdentifiedEntity> {

	E map(D dto);
	
	D inverseMap(E entity);
}
