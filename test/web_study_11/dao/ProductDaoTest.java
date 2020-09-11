package web_study_11.dao;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import web_study_11.dao.impl.ProductDaoImpl;
import web_study_11.ds.JdbcUtil;
import web_study_11.dto.Product;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest {

	private static Connection con;
	private ProductDao dao;

	@Before
	public void setUp() throws Exception {
		con = JdbcUtil.getConnection();
		dao = ProductDaoImpl.getInstance();
		((ProductDaoImpl) dao).setCon(con);
	}

	@After
	public void tearDown() throws Exception {
		dao = null;
	}

	@Test
	public void test05SelectProductByAll() {
		System.out.println("test05SelectProductByAll");
		List<Product> list = dao.selectProductByAll();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test01InsertProduct() {
		System.out.printf("%s()%n", "test01InsertProduct");
		Product insertProduct = new Product("개념", 27000, "aa.jpg", "aaaa");
		int res = dao.insertProduct(insertProduct);
		Assert.assertEquals(1, res);

		System.out.printf("%s%n%n", insertProduct);
	}

	@Test
	public void test04SelectProductByCode() {
		System.out.printf("%s()%n", "test04SelectProductByCode");
		Product selectProduct = ProductDaoImpl.getInstance().selectProductByCode(new Product(11));
		Assert.assertNotNull(selectProduct);

		System.out.printf("%s%n%n", selectProduct);
	}

	@Test
	public void test02UpdateProduct() {
		System.out.printf("%s()%n", "test02UpdateProduct");
		Product updateProduct = new Product(12, "개념2", 30000, "bb.jpg", "bbbb");
		int res = dao.updateProduct(updateProduct);
		Assert.assertEquals(1, res);

		System.out.printf("%s%n%n", updateProduct);
	}

	@Test
	public void test03DeleteProduct() {
		System.out.printf("%s()%n", "test03DeleteProduct");
		Product deleteProduct = new Product(8);
		int res = dao.deleteProduct(deleteProduct);
		Assert.assertEquals(1, res);

		System.out.printf("%s%n%n", deleteProduct);
	}

}
