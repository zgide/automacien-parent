package com.canalplus.automaticien.repository.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "CONTRACT")
@Entity
public class Contract implements IdentifiedEntity {

	@Id
	Long id;

	@Column(name = "USER_ID", insertable = false, updatable = false, nullable = false)
	private Long UserId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;

	@Column(name = "ADRESS_ID", insertable = false, updatable = false, nullable = false)
	private Long adressId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADRESS_ID")
	private Address adress;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return UserId;
	}

	public void setUserId(Long userId) {
		UserId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getAdressId() {
		return adressId;
	}

	public void setAdressId(Long adressId) {
		this.adressId = adressId;
	}

	public Address getAdress() {
		return adress;
	}

	public void setAdress(Address adress) {
		this.adress = adress;
	}
}
