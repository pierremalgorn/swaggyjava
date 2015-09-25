package com.rousseaumalgorn.service.impl;

import java.util.List;

import com.rousseaumalgorn.entity.Computer;
import com.rousseaumalgorn.dao.ComputerDao;
import com.rousseaumalgorn.dao.impl.ComputerDaoImpl;
import com.rousseaumalgorn.service.ComputerService;


public class ComputerServiceImpl implements ComputerService {

	private ComputerDao ComputerDao;
	
	private static ComputerServiceImpl INSTANCE = null;
	
	public static ComputerServiceImpl getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new ComputerServiceImpl();
		}
		return INSTANCE;
	}
	
	private ComputerServiceImpl() {
		this.ComputerDao = ComputerDaoImpl.getInstance();
	}
	
	@Override
	public List<Computer> getAll() {
		return ComputerDao.getAll();
	}

	@Override
	public List<Computer> searchComputerName(String search, int pageSize, int pageNumber) {
		return ComputerDao.searchComputerName(search, pageSize, pageNumber);
	}

	@Override
	public Long getNbResults(String search) {
		return ComputerDao.getNbResults(search);
	}

	@Override
	public void addComputer(String name, String introduced,
			String discontinued, String company) {
		ComputerDao.addComputer(name, introduced, discontinued, company);
		
	}

//	@Override
//	public Computer getById(Long id) {
//		return ComputerDao.getById(id);
//	}

}