package com.canalplus.automaticien.repository.dao;

import com.canalplus.automaticien.fmk.dto.IdentifiedDto;

public interface ICrudDao<D extends IdentifiedDto> {

	Long save(D dto);
}
