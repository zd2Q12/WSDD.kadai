package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Products;

public class ProductsDaoImpl implements ProductsDao {

	private DataSource ds;

	public ProductsDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Products> findAll() throws Exception {
		List<Products> productList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "select id, name, price from products";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				productList.add(mapToProducts(rs));
			}
		} catch (Exception e) {
			throw e;
		}

		return productList;
	}

	private Products mapToProducts(ResultSet rs) throws Exception{
		Products products = new Products();
		products.setId(rs.getInt("id"));
		products.setName(rs.getString("name"));
		products.setPrice( rs.getInt("price"));
		return products;
	}

	@Override
	public Products findById(Integer id) throws Exception {
		Products product = null;
		try (Connection con = ds.getConnection()) {
			String sql = "select id, name, price from products where id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				product = mapToProducts(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return product;
	}

	@Override
	public void insert(Products product) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "insert into products(name, price)values(?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, product.getName());
			stmt.setObject(2, product.getPrice(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(Products product) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void delete(Products product) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
