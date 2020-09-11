package web_study_11.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import web_study_11.dto.Product;
import web_study_11.service.ProductService;

@WebServlet("/productUpdate.do")
public class ProductUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ProductService service;

	public void init() throws ServletException {
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
			
			int pdtCode = Integer.parseInt(request.getParameter("code").trim());
			Product product = service.getPdtCode(new Product(pdtCode));
			System.out.println("product > " + product);
			
			request.setAttribute("product", product);
			
			request.getRequestDispatcher("product/productUpdate.jsp").forward(request, response);

		} else {
			System.out.println("POST");
			
			request.setCharacterEncoding("UTF-8");

			ServletContext context = getServletContext();
			String path = context.getRealPath("upload");
			System.out.println(path);
			String encType = "UTF-8";
			int sizeLimit = 20 * 1024 * 1024;

			MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, encType,
					new DefaultFileRenamePolicy());
			
			String code = multi.getParameter("code");
			String name = multi.getParameter("name");
			int price = Integer.parseInt(multi.getParameter("price"));
			String description = multi.getParameter("description");
			String pictureUrl = multi.getFilesystemName("pictureUrl");
			if (pictureUrl == null) {
				pictureUrl = multi.getParameter("nonmakeImg");
			}

			Product pdt = new Product();
			pdt.setCode(Integer.parseInt(code));
			pdt.setName(name);
			pdt.setPrice(price);
			pdt.setDescription(description);
			pdt.setPictureUrl(pictureUrl);

			int res = service.modifyPdt(pdt);

			response.getWriter().print(res);
			response.sendRedirect("productList.do");
		}

	}

}
