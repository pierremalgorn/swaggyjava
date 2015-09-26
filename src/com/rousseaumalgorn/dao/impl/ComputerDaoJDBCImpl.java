package com.rousseaumalgorn.dao.impl;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.rousseaumalgorn.dao.ComputerDao;
import com.rousseaumalgorn.dao.utils.DaoUtils;
import com.rousseaumalgorn.entity.Company;
import com.rousseaumalgorn.entity.Computer;
import com.rousseaumalgorn.exception.DAOException;

public class ComputerDaoJDBCImpl implements ComputerDao {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String USER = "java";
	private static final String PASSWORD = "root";

	private static ComputerDao INSTANCE = null;
	
	private ComputerDaoJDBCImpl(){
	}
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			throw new RuntimeException("Can not load Driver", e);
		}
	}
	
	public static ComputerDao getInstance(){
		if(INSTANCE == null) {
			INSTANCE = new ComputerDaoJDBCImpl();
		}
		return INSTANCE;
	}
	
	@Override
	public List<Computer> getAll() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			statement = (Statement) connection.createStatement();
			String sql = "SELECT `computer`.`id`, `computer`.`name`, `introduced`, `discontinued`, `company`.`id` AS `company_id`, `company`.`name` AS `company_name` FROM `computer` LEFT JOIN `company` ON `company_id` = `company`.`id`";
			resultSet = statement.executeQuery(sql);
			List<Computer> computerList = new ArrayList<Computer>();
			while (resultSet.next()) {
				Computer computer = new Computer(resultSet.getLong("id"), resultSet.getString("name"), (Date)resultSet.getTimestamp("introduced"), (Date)resultSet.getTimestamp("discontinued"), new Company(resultSet.getLong("company_id"), resultSet.getString("company_name")));
				computerList.add(computer);
			}
			return computerList;
		} catch (SQLException e) {
			throw new DAOException("Erreur getAll()", e);
		} finally {
			DaoUtils.closeAll(resultSet, statement, connection);
		}
	}

	@Override
	public Long getNbResults(String search) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			statement = connection.prepareStatement(""
					+ "SELECT Count(*)"
					+ "FROM `computer`"
					+ "WHERE `computer`.`name` LIKE ? ");
			statement.setString(1, "%"+search+"%");
			resultSet = statement.executeQuery();
			
			resultSet.next();
			return resultSet.getLong(1);
			
		} catch (SQLException e) {
			throw new DAOException("Erreur getAll()", e);
		} finally {
			DaoUtils.closeAll(resultSet, statement, connection);
		}
	}

	@Override
	public List<Computer> searchComputerName(String search, int pageSize,
			int pageNumber) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			statement = connection.prepareStatement(""
					+ "SELECT `computer`.`id`,"
					+ " `computer`.`name`, "
					+ "`introduced`, `discontinued`, "
					+ "`company`.`id` AS `company_id`, "
					+ "`company`.`name` AS `company_name` "
					+ "FROM `computer` LEFT JOIN `company` ON `company_id` = `company`.`id`"
					+ "WHERE `computer`.`name` LIKE ? "
					+ "LIMIT ? OFFSET ?");
			statement.setString(1, "%"+search+"%");
			statement.setInt(2, pageSize);
			statement.setInt(3, (pageNumber-1) * pageSize);
			resultSet = statement.executeQuery();
			
			List<Computer> computerList = new ArrayList<Computer>();
			while (resultSet.next()) {
				Computer computer = new Computer(resultSet.getLong("id"), resultSet.getString("name"), (Date)resultSet.getTimestamp("introduced"), (Date)resultSet.getTimestamp("discontinued"), new Company(resultSet.getLong("company_id"), resultSet.getString("company_name")));
				computerList.add(computer);
			}
			return computerList;
		} catch (SQLException e) {
			throw new DAOException("Erreur searchComputerName()", e);
		} finally {
			DaoUtils.closeAll(resultSet, statement, connection);
		}
	}

	@Override
	public void addComputer(String name, String introduced, String discontinued, String company) {
		Connection connection = null;
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		ResultSet resultSet = null;
		try {
			//On récupère l'id company avant
			Long companyId;
			connection = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			statement = connection.prepareStatement(""
					+ "SELECT `id`"
					+ "FROM `company`"
					+ "WHERE `name` LIKE ? ");
			statement.setString(1, company);
			resultSet = statement.executeQuery();
			
			resultSet.next();
			companyId = resultSet.getLong(1);
			
			statement2 = connection.prepareStatement("INSERT INTO computer VALUES (NULL, ?, ?, ?, ?)");
			statement2.setString(1, name);
			statement2.setString(2, introduced);
			statement2.setString(3, discontinued);
			statement2.setLong(4, companyId);
			statement2.execute();
			
		} catch (SQLException e) {
			throw new DAOException("Erreur addComputer()", e);
		} finally {
			DaoUtils.closeAll(resultSet, statement, connection);
			DaoUtils.closeAll(resultSet, statement2, connection);
		}
		
	}

	@Override
	public void deleteComputer(Long id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			statement = connection.prepareStatement("DELETE FROM `computer` WHERE `id` = ?");
			statement.setLong(1, id);
			statement.execute();
			
		} catch (SQLException e) {
			throw new DAOException("Erreur deleteComputer()", e);
		} finally {
			DaoUtils.closeAll(resultSet, statement, connection);
		}
		
	}	
	
}
