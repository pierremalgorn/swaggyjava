package com.rousseaumalgorn.dao;

import java.util.List;

import com.rousseaumalgorn.entity.Computer;

public interface ComputerDao {

	List<Computer> getAll();

	List<Computer> searchComputerName(String search, int pageSize);

}
