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
	//pageSize: nombre de résultats à afficher; pageNumber: numéro de page
	public List<Computer> searchComputerName(String search, int pageSize, int pageNumber) {
		EntityManagerFactory entityManagerFactory = DaoManager.getInstance().getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//On get les résultats correspondants à la plage sélectionnée
		return entityManager.createQuery("select c from Computer c WHERE c.name LIKE :compName")
				.setParameter("compName", "%"+search+"%")
				.setFirstResult((pageNumber-1) * pageSize)
				.setMaxResults(pageSize)
				.getResultList();
		
	}
	
	//Retourne le nombre de résultats pour une recherche donnée
	public Long getNbResults(String search) {
		EntityManagerFactory entityManagerFactory = DaoManager.getInstance().getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return (Long)entityManager.createQuery("select count(*) from Computer c WHERE c.name LIKE :compName")
				.setParameter("compName", "%"+search+"%")
				.getSingleResult();
	}

	@Override
	public void addComputer(String name, String introduced,	String discontinued, String company) {
		EntityManagerFactory entityManagerFactory = DaoManager.getInstance().getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Company companyObject = (Company)entityManager.createQuery("select c from Company c WHERE c.name LIKE :compName")
				.setParameter("compName", "%"+company+"%")
				.getSingleResult();	
		
		//On instance un nouveau Computer pour l'injecter dans la BDD
		Computer computer = new Computer();
		computer.setName(name);
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		computer.setCompany(companyObject);	
		
		//On fait une transaction pour appliquer les changements dans la base
		entityManager.getTransaction().begin();
        // persist object - add to entity manager
        entityManager.persist(computer);
        // flush entityManager - save to DB
        entityManager.flush();	    
	    // commit transaction
	    entityManager.getTransaction().commit();		
		
	}

	@Override
	public void deleteComputer(Long id) {
		EntityManagerFactory entityManagerFactory = DaoManager.getInstance().getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//On recherche dans la base le computer à supprimer grâce à l'id
		Computer computerToDelete = entityManager.find(Computer.class, id);		
		
		//On fait une transaction pour appliquer les changements dans la base
		entityManager.getTransaction().begin();
        // persist object - add to entity manager
		entityManager.remove(computerToDelete);
        // flush entityManager - save to DB
        entityManager.flush();	    
	    // commit transaction
	    entityManager.getTransaction().commit();	
		
	}
	
	
	
}
