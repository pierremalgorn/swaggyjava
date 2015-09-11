package com.rousseaumalgorn.controller;

import java.io.IOException;

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
		//Trying hello world console output when calling Servlet
		request.setAttribute("computers", ComputerService.getAll());		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/dashboard.jsp"));		
		rd.forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		ComputerDao ComputerDao = ComputerDaoImpl.getInstance();
//		String login = request.getParameter("login");
//		String password = request.getParameter("password");
//		ComputerDao.create(new Computer(null, login, password));
//		this.doGet(request, response);
//	}

}