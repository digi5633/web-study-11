package web_study_11.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web_study_11.dao.ProductDao;
import web_study_11.dto.Product;

public class ProductDaoImpl implements ProductDao {

	private static final ProductDaoImpl instance = new ProductDaoImpl();

	private Connection con;

	public void setCon(Connection con) {
		this.con = con;
	}

	public ProductDaoImpl() {

	}

	public static ProductDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<Product> selectProductByAll() {
		String sql = "SELECT CODE, NAME, PRICE, PICTUREURL, DESCRIPTION FROM PRODUCT ORDER BY CODE";
		try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				ArrayList<Product> list = new ArrayList<Product>();
				do {
					list.add(getProduct(rs));
				} while (rs.next());
				return list;
			}

		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	@Override
	public int insertProduct(Product pdt) {
		String sql = "INSERT INTO PRODUCT(NAME, PRICE, PICTUREURL, DESCRIPTION) VALUES (?, ?, ?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, pdt.getName());
			pstmt.setInt(2, pdt.getPrice());
			pstmt.setString(3, pdt.getDescription());
			pstmt.setString(4, pdt.getPictureUrl());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}

	}

	@Override
	public Product selectProductByCode(Product pdt) {
		String sql = "SELECT CODE, NAME, PRICE, PICTUREURL, DESCRIPTION FROM PRODUCT WHERE CODE = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, pdt.getCode());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getProduct(rs);
				}
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	private Product getProduct(ResultSet rs) throws SQLException {
		int code = rs.getInt("CODE");
		String name = rs.getString("NAME");
		int price = rs.getInt("PRICE");
		String description = rs.getString("PICTUREURL");
		String pictureUrl = rs.getString("DESCRIPTION");
		return new Product(code, name, price, description, pictureUrl);
	}

	@Override
	public int updateProduct(Product pdt) {
		String sql = "UPDATE PRODUCT SET NAME = ?, PRICE = ?, PICTUREURL = ?, DESCRIPTION = ? WHERE CODE = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, pdt.getName());
			pstmt.setInt(2, pdt.getPrice());
			pstmt.setString(3, pdt.getDescription());
			pstmt.setString(4, pdt.getPictureUrl());
			pstmt.setInt(5, pdt.getCode());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}

	}

	@Override
	public int deleteProduct(Product pdt) {
		String sql = "DELETE FROM PRODUCT WHERE CODE = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, pdt.getCode());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}

	}

}
