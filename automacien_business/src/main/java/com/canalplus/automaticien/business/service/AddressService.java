package com.canalplus.automaticien.business.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.canalplus.automaticien.fmk.dto.AddressDto;
import com.canalplus.automaticien.fmk.dto.ContractDto;
import com.canalplus.automaticien.fmk.dto.UserAddressPeriodDto;
import com.canalplus.automaticien.repository.dao.IAddressDao;
import com.canalplus.automaticien.repository.dao.IContractDao;
import com.canalplus.automaticien.repository.dao.IUserAddressPeriodDao;

@Service
public class AddressService implements IAddressService {

	@Autowired
	private IAddressDao addressDao;
	@Autowired
	private IUserAddressPeriodDao userAddressPeriodDao;
	@Autowired
	private IContractDao contractDao;

	@Override
	@Transactional
	public void updateSubscriberAddress(AddressDto addressDto) {
		if (addressDto.getId() == null) {
			addressDto.setId(addressDao.save(addressDto));
		}
		closeCurrentAssociationPeriod(addressDto);
		createNewAssociationPeriod(addressDto);
		List<ContractDto> contracts = contractDao.getContractsByUserId(addressDto.getUserId());
		contracts.forEach(contract -> {
			contract.setAddressId(addressDto.getId());
			contractDao.update(contract);
		});

	}

	private void closeCurrentAssociationPeriod(AddressDto addressDto) {
		UserAddressPeriodDto currentPeriod = userAddressPeriodDao
				.getAddressPeriodByUserIdAndDate(addressDto.getUserId());
		currentPeriod.setEndDateTime(LocalDateTime.now().minusSeconds(1L));
		userAddressPeriodDao.Update(currentPeriod);
	}

	private void createNewAssociationPeriod(AddressDto addressDto) {
		var newPeriod = new UserAddressPeriodDto();
		newPeriod.setAddressId(addressDto.getId());
		newPeriod.setUserId(addressDto.getUserId());
		newPeriod.setStartDateTime(LocalDateTime.now());
		userAddressPeriodDao.save(newPeriod);
	}
}
