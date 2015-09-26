package com.rousseaumalgorn.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.rousseaumalgorn.dao.CompanyDao;
import com.rousseaumalgorn.dao.manager.DaoManager;
import com.rousseaumalgorn.entity.Company;

public class CompanyDaoImpl implements CompanyDao {
	
private static CompanyDao INSTANCE = null;
	
	private CompanyDaoImpl(){
	}
	
	public static CompanyDao getInstance(){
		if(INSTANCE == null) {
			INSTANCE = new CompanyDaoImpl();
		}
		return INSTANCE;
	}

	@Override
	public List<Company> getAll() {
		EntityManagerFactory entityManagerFactory = DaoManager.getInstance().getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.createQuery("select c from Company c").getResultList();
	}

}
