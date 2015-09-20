package com.rousseaumalgorn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rousseaumalgorn.dao.ComputerDao;
import com.rousseaumalgorn.dao.impl.ComputerDaoImpl;
import com.rousseaumalgorn.entity.Computer;
import com.rousseaumalgorn.service.ComputerService;
import com.rousseaumalgorn.service.impl.ComputerServiceImpl;


@WebServlet("/ComputerServlet")
public class ComputerServlet  extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComputerService ComputerService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputerServlet() {
        super();
        ComputerService = ComputerServiceImpl.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long computerNb = 0L;
		List<Computer> computerList;
		
		int pageSize;
		if(request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		} else {
			//Par défaut : 5 items par page
			pageSize = 5;
		}
		
		int pageNumber;
		if(request.getParameter("page") != null) {
			pageNumber = Integer.parseInt(request.getParameter("page"));
		} else {
			//Par défaut : page 1
			pageNumber = 1;
		}
		
		if(request.getParameter("search") != null) {
			String search = request.getParameter("search");
			computerNb = ComputerService.getNbResults(search);
			computerList = ComputerService.searchComputerName(search, pageSize, pageNumber);
		} else {		
			computerList = ComputerService.getAll();		
		}
		
		request.setAttribute("computers", computerList);
		request.setAttribute("size", computerNb);
		request.setAttribute("nbPages", Math.ceil((double)computerNb / (double)pageSize));
		request.setAttribute("page", pageNumber);
		
		if(request.getParameter("update") == null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/dashboard.jsp"));		
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/table.jsp"));		
			rd.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
//		ComputerDao ComputerDao = ComputerDaoImpl.getInstance();
//		String login = request.getParameter("login");
//		String password = request.getParameter("password");
//		ComputerDao.create(new Computer(null, login, password));
//		this.doGet(request, response);
	}

}