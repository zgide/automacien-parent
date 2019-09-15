package com.canalplus.automaticien.business.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.canalplus.automaticien.business.service.IAddressService;
import com.canalplus.automaticien.fmk.dto.AddressDto;
import com.canalplus.automaticien.fmk.security.PermissionConsts;

@RestController
public class AddressCtrl {

	@Autowired
	private IAddressService addressService;

	@PreAuthorize("hasRole('" + PermissionConsts.role_advisor + "')")
	@PostMapping("/address")
	public void updateAddress(@RequestBody AddressDto address) {
		addressService.updateSubscriberAddress(address);
	}
}
