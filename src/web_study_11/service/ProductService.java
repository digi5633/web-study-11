package web_study_11.service;

import java.sql.Connection;
import java.util.List;

import web_study_11.dao.ProductDao;
import web_study_11.dao.impl.ProductDaoImpl;
import web_study_11.ds.JdbcUtil;
import web_study_11.dto.Product;

public class ProductService {

	private static Connection con;
	private ProductDao dao;

	public ProductService() {
		con = JdbcUtil.getConnection();
		dao = ProductDaoImpl.getInstance();
		((ProductDaoImpl) dao).setCon(con);
	}

	public List<Product> showProduct() {
		return dao.selectProductByAll();
	}

}
