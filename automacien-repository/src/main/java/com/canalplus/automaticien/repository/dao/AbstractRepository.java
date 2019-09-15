package com.canalplus.automaticien.repository.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.canalplus.automaticien.repository.entities.IdentifiedEntity;
import org.springframework.stereotype.Repository;;

@Repository
public class AbstractRepository {

	@PersistenceContext
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	protected <E extends IdentifiedEntity> Long save(E entity) {
		getEntityManager().persist(entity);
		return entity.getId();
	}

	protected <E extends IdentifiedEntity> void update(E entity) {
		getEntityManager().merge(entity);
	}
}
