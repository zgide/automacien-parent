package com.canalplus.automaticien.repository.dao;

import java.util.List;

import com.canalplus.automaticien.fmk.dto.ContractDto;

public interface IContractDao extends ICrudDao<ContractDto> {

	List<ContractDto> getContractsByUserId(Long userId);

	void update(ContractDto contract);


}
