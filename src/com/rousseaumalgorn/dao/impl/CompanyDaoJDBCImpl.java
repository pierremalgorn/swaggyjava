package com.rousseaumalgorn.dao.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.rousseaumalgorn.dao.CompanyDao;
import com.rousseaumalgorn.dao.utils.DaoUtils;
import com.rousseaumalgorn.entity.Company;
import com.rousseaumalgorn.exception.DAOException;

public class CompanyDaoJDBCImpl implements CompanyDao {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String USER = "java";
	private static final String PASSWORD = "root";

	private static CompanyDao INSTANCE = null;
	
	private CompanyDaoJDBCImpl(){
	}
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			throw new RuntimeException("Can not load Driver", e);
		}
	}
	
	public static CompanyDao getInstance(){
		if(INSTANCE == null) {
			INSTANCE = new CompanyDaoJDBCImpl();
		}
		return INSTANCE;
	}
	
	@Override
	public List<Company> getAll() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			statement = (Statement) connection.createStatement();
			String sql = "SELECT * FROM `company`";
			resultSet = statement.executeQuery(sql);
			
			List<Company> companyList = new ArrayList<Company>();
			while (resultSet.next()) {
				Company company = new Company(resultSet.getLong("id"), resultSet.getString("name"));
				companyList.add(company);
			}
			return companyList;
		} catch (SQLException e) {
			throw new DAOException("Erreur getAll()", e);
		} finally {
			DaoUtils.closeAll(resultSet, statement, connection);
		}
	}

}