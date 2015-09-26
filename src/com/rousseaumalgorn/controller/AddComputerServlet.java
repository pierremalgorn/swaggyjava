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
import com.rousseaumalgorn.entity.Company;
import com.rousseaumalgorn.entity.Computer;
import com.rousseaumalgorn.service.CompanyService;
import com.rousseaumalgorn.service.ComputerService;
import com.rousseaumalgorn.service.impl.CompanyServiceImpl;
import com.rousseaumalgorn.service.impl.ComputerServiceImpl;

@WebServlet("/AddComputerServlet")
public class AddComputerServlet  extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CompanyService CompanyService;
	private ComputerService ComputerService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputerServlet() {
        super();
        CompanyService = CompanyServiceImpl.getInstance();
        ComputerService = ComputerServiceImpl.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		List<Company> companies = CompanyService.getAll();
		request.setAttribute("companies", companies);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/addComputer.jsp"));		
		rd.forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String company = request.getParameter("company");
		ComputerService.addComputer(name, introduced, discontinued, company);
		response.sendRedirect("ComputerServlet");
	}

}