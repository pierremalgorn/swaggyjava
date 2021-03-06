package com.rousseaumalgorn.service.impl;

import java.util.List;

import com.rousseaumalgorn.dao.CompanyDao;
import com.rousseaumalgorn.dao.impl.CompanyDaoImpl;
import com.rousseaumalgorn.dao.impl.CompanyDaoJDBCImpl;
import com.rousseaumalgorn.entity.Company;
import com.rousseaumalgorn.service.CompanyService;

//Singleton
public class CompanyServiceImpl implements CompanyService {

	private CompanyDao CompanyDao;
	
	private static CompanyServiceImpl INSTANCE = null;
	
	public static CompanyServiceImpl getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new CompanyServiceImpl();
		}
		return INSTANCE;
	}
	
	private CompanyServiceImpl() {
		this.CompanyDao = CompanyDaoJDBCImpl.getInstance();
	}
	
	@Override
	public List<Company> getAll() {
		return CompanyDao.getAll();
	}

}
