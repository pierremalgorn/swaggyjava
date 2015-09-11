package com.rousseaumalgorn.dao.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.rousseaumalgorn.dao.ComputerDao;
import com.rousseaumalgorn.dao.manager.DaoManager;
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
	
//	@Override
//	public List<Computer> getAll() {
//		Connection connection = null;
//		Statement statement = null;
//		ResultSet resultSet = null;
//		try {
//			connection = DriverManager.getConnection(URL, USER, PASSWORD);
//			statement = connection.createStatement();
//			String sql = "SELECT * FROM computer";
//			resultSet = statement.executeQuery(sql);
//			List<Computer> computerList = new ArrayList<Computer>();
//			while (resultSet.next()) {
//				Computer computer = new Computer(resultSet.getInt("id"), resultSet.getString("name"), ((Timestamp)resultSet.getLong("introduced")).getTime(), resultSet.getLong("name"), 0);
//				computerList.add(computer);
//			}
//			return computerList;
//		} catch (SQLException e) {
//			throw new DAOException("TODO better message", e);
//		} finally {
//			DaoUtils.closeAll(resultSet, statement, connection);
//		}
//	}
//
//	@Override
//	public User getById(Long id) {
//		Connection connection = null;
//		PreparedStatement statement = null;
//		ResultSet resultSet = null;
//		try {
//			connection = DriverManager.getConnection(URL, USER, PASSWORD);
//			statement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
//			statement.setLong(1, id);
//			resultSet = statement.executeQuery();
//			List<User> userList = new ArrayList<User>();
//			while (resultSet.next()) {
//				User user = User.builder()
//						.setId(resultSet.getInt("id"))
//						.setLogin(resultSet.getString("login"))
//						.setPassword(resultSet.getString("password"))
//						.build();
//				userList.add(user);
//			}
//			if(userList.size() > 1) {
//				throw new DAOException("Database incorrect, duplicate id :"+id);
//			} else if (userList.size() == 1) {
//				return userList.get(0);
//			} else {
//				return null;
//			}
//		} catch (SQLException e) {
//			throw new DAOException("TODO better message");
//		} finally {
//			DaoUtils.closeAll(resultSet, statement, connection);
//		}
//	}
//
//	@Override
//	public void insert(User user) {
//		// TODO
//	}
	
	public static ComputerDao getInstance(){
		if(INSTANCE == null) {
			INSTANCE = new ComputerDaoImpl();
		}
		return INSTANCE;
	}

	@Override
	public List<Computer> searchComputerName(String search, int pageSize) {
		int pageNumber = 1;
		EntityManagerFactory entityManagerFactory = DaoManager.getInstance().getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.createQuery("select c from Computer c WHERE c.name LIKE :custName")
				.setParameter("custName", "%"+search+"%")
				.setFirstResult((pageNumber-1) * pageSize)
				.setMaxResults(pageSize)
				.getResultList();
		
	}
	
}
