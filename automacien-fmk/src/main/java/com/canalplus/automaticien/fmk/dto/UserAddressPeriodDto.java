 package com.canalplus.automaticien.fmk.dto;

import java.time.LocalDateTime;

public class UserAddressPeriodDto implements IdentifiedDto {

	private Long id;
	
	private Long userId;
	
	private Long AddressId;
	
	private LocalDateTime startDateTime;

	private LocalDateTime EndDateTime;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAddressId() {
		return AddressId;
	}

	public void setAddressId(Long addressId) {
		AddressId = addressId;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return EndDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		EndDateTime = endDateTime;
	}

}
