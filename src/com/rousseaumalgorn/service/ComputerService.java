package com.rousseaumalgorn.service;

import java.util.List;

import com.rousseaumalgorn.entity.Computer;

public interface ComputerService {

	List<Computer> getAll();

	Long getNbResults(String search);

	List<Computer> searchComputerName(String search, int pageSize, int pageNumber);

	void addComputer(String name, String introduced, String discontinued,
			String company);

	void deleteComputer(Long id);

}
