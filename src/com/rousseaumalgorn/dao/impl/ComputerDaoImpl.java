package com.rousseaumalgorn.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.rousseaumalgorn.dao.ComputerDao;
import com.rousseaumalgorn.dao.manager.DaoManager;
import com.rousseaumalgorn.entity.Company;
import com.rousseaumalgorn.entity.Computer;

import javax.persistence.EntityManager;


public class ComputerDaoImpl implements ComputerDao{
//	private static final String URL = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
//	private static final String USER = "java";
//	private static final String PASSWORD = "root";

	private static ComputerDao INSTANCE = null;
	
	private ComputerDaoImpl(){
	}
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			throw new RuntimeException("Can not load Driver", e);
		}
	}
	
	@Override
	public List<Computer> getAll() {
		EntityManagerFactory entityManagerFactory = DaoManager.getInstance().getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.createQuery("select c from Computer c").getResultList();
	}
	
	public static ComputerDao getInstance(){
		if(INSTANCE == null) {
			INSTANCE = new ComputerDaoImpl();
		}
		return INSTANCE;
	}

	@Override
	public List<Computer> searchComputerName(String search, int pageSize, int pageNumber) {
		EntityManagerFactory entityManagerFactory = DaoManager.getInstance().getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.createQuery("select c from Computer c WHERE c.name LIKE :custName")
				.setParameter("custName", "%"+search+"%")
				.setFirstResult((pageNumber-1) * pageSize)
				.setMaxResults(pageSize)
				.getResultList();
		
	}
	
	public Long getNbResults(String search) {
		EntityManagerFactory entityManagerFactory = DaoManager.getInstance().getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return (Long)entityManager.createQuery("select count(*) from Computer c WHERE c.name LIKE :custName")
				.setParameter("custName", "%"+search+"%")
				.getSingleResult();
	}

	@Override
	public void addComputer(String name, String introduced,	String discontinued, String company) {
		EntityManagerFactory entityManagerFactory = DaoManager.getInstance().getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Company companyObject = (Company)entityManager.createQuery("select c from Company c WHERE c.name LIKE :compName")
				.setParameter("compName", "%"+company+"%")
				.getSingleResult();	
		
		Computer computer = new Computer();
		computer.setName(name);
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		computer.setCompany(companyObject);	
		
		entityManager.getTransaction().begin();
        // persist object - add to entity manager
        entityManager.persist(computer);
        // flush entityManager - save to DB
        entityManager.flush();	    
	    // commit transaction at all
	    entityManager.getTransaction().commit();		
		
	}

	@Override
	public void deleteComputer(Long id) {
		EntityManagerFactory entityManagerFactory = DaoManager.getInstance().getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Computer computerToDelete = entityManager.find(Computer.class, id);		
		
		entityManager.getTransaction().begin();
        // persist object - add to entity manager
		entityManager.remove(computerToDelete);
        // flush entityManager - save to DB
        entityManager.flush();	    
	    // commit transaction at all
	    entityManager.getTransaction().commit();	
		
	}
	
	
	
}
