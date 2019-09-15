package com.canalplus.automaticien.repository.dao;

import com.canalplus.automaticien.fmk.dto.UserAddressPeriodDto;

public interface IUserAddressPeriodDao extends ICrudDao<UserAddressPeriodDto> {

	void Update(UserAddressPeriodDto dto);

	UserAddressPeriodDto getAddressPeriodByUserIdAndDate(Long userId);

}
