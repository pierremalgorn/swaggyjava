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


@WebServlet("/ComputerServlet")
public class ComputerServlet  extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComputerService ComputerService;
	private CompanyService CompanyService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputerServlet() {
        super();
        ComputerService = ComputerServiceImpl.getInstance();
        CompanyService = CompanyServiceImpl.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Si requête AJAX d'update
		if(request.getParameter("update") != null) {
			Long computerNb = 0L;
			List<Computer> computerList;		
			int pageSize;		
			int pageNumber;
			
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
			pageNumber = Integer.parseInt(request.getParameter("page"));
			
			String search = request.getParameter("search");
			computerNb = ComputerService.getNbResults(search);
			computerList = ComputerService.searchComputerName(search, pageSize, pageNumber);
			
			request.setAttribute("computers", computerList);
			request.setAttribute("size", computerNb);
			request.setAttribute("nbPages", Math.ceil((double)computerNb / (double)pageSize));
			request.setAttribute("page", pageNumber);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/table.jsp"));		
			rd.forward(request, response);
		} else if(request.getParameter("delete") != null){
			Long id = Long.parseLong(request.getParameter("id"));
			ComputerService.deleteComputer(id);
			response.sendRedirect("ComputerServlet");
		} else {
			List<Company> companies = CompanyService.getAll();
			request.setAttribute("companies", companies);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/dashboard.jsp"));		
			rd.forward(request, response);
		}
		
		
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