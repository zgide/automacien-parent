package com.canalplus.automaticien.repository.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.canalplus.automaticien.fmk.dto.ContractDto;
import com.canalplus.automaticien.repository.entities.Address;
import com.canalplus.automaticien.repository.entities.Contract;
import com.canalplus.automaticien.repository.entities.Contract_;
import com.canalplus.automaticien.repository.entities.User;
import com.canalplus.automaticien.repository.mapper.ContractMapper;

@Repository
public class ContractDao extends AbstractRepository implements IContractDao {

	@Autowired
	private ContractMapper mapper;

	@Override
	public Long save(ContractDto dto) {
		Contract entity = mapContract(dto);
		save(entity);
		return entity.getId();
	}

	private Contract mapContract(ContractDto dto) {
		Contract entity = mapper.map(dto);
		User user = getEntityManager().getReference(User.class, dto.getUserId());
		entity.setUser(user);
		Address address = getEntityManager().getReference(Address.class, dto.getAddressId());
		entity.setAdress(address);
		return entity;
	}

	@Override
	public List<ContractDto> getContractsByUserId(Long userId) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Contract> query = criteriaBuilder.createQuery(Contract.class);
		Root<Contract> root = query.from(Contract.class);
		query.where(criteriaBuilder.equal(root.get(Contract_.UserId), userId));
		List<Contract> contracts = getEntityManager().createQuery(query).getResultList();
		return contracts.stream().map(mapper::inverseMap).collect(Collectors.toList());
	}

	@Override
	public void update(ContractDto contract) {
		Contract entity = mapContract(contract);
		getEntityManager().merge(entity);
	}

}
