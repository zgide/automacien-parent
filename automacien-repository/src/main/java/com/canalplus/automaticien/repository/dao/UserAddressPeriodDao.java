package com.canalplus.automaticien.repository.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.canalplus.automaticien.fmk.dto.UserAddressPeriodDto;
import com.canalplus.automaticien.repository.entities.Address;
import com.canalplus.automaticien.repository.entities.User;
import com.canalplus.automaticien.repository.entities.UserAddressPeriod;
import com.canalplus.automaticien.repository.entities.UserAddressPeriod_;
import com.canalplus.automaticien.repository.mapper.UserAddressPeriodMapper;

@Repository
public class UserAddressPeriodDao extends AbstractRepository implements IUserAddressPeriodDao {

	@Autowired
	private UserAddressPeriodMapper mapper;

	@Override
	public Long save(UserAddressPeriodDto dto) {
		UserAddressPeriod entity = mapEntity(dto);
		save(entity);
		return entity.getId();
	}

	@Override
	public void Update(UserAddressPeriodDto dto) {
		UserAddressPeriod entity = mapEntity(dto);
		update(entity);
	}

	private UserAddressPeriod mapEntity(UserAddressPeriodDto dto) {
		UserAddressPeriod entity = mapper.map(dto);
		User user = getEntityManager().getReference(User.class, dto.getUserId());
		entity.setUser(user);
		Address address = getEntityManager().getReference(Address.class, dto.getAddressId());
		entity.setAddress(address);
		return entity;
	}

	@Override
	public UserAddressPeriodDto getAddressPeriodByUserIdAndDate(Long userId) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserAddressPeriod> query = criteriaBuilder.createQuery(UserAddressPeriod.class);
	    Root<UserAddressPeriod> root = query.from(UserAddressPeriod.class);
		query.where(criteriaBuilder.isNull(root.get(UserAddressPeriod_.endDateTime)));
	    UserAddressPeriod period = getEntityManager().createQuery(query).getSingleResult();
		return mapper.inverseMap(period);
	}

}
