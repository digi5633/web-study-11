package web_study_11.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web_study_11.service.ProductService;

@WebServlet("/productWrite.do")
public class ProductWriteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ProductService service;

	public void init(ServletConfig config) throws ServletException {
		service = new ProductService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Process(request, response);
	}

	private void Process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("GET");

			request.getRequestDispatcher("product/productWrite.jsp").forward(request, response);

		} else {
			System.out.println("POST");
		}
		
	}

}
