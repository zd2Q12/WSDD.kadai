package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import domain.Purchase;

public class PurchaseDaoImpl implements PurchaseDao {

	private DataSource ds;

	public PurchaseDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Purchase> findAll() throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Purchase findById(Integer id) throws Exception {
		Purchase purchase = null;
		String sql = "SELECT user_id, products_id, meats_id, pickup_time, purchased_at"
				+ " FROM purchase where id = ?";

		try (Connection con = ds.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				purchase = new Purchase();
				purchase.setUsersId(rs.getInt("users_id"));
				purchase.setProductsId(rs.getInt("products_id"));
				purchase.setMeatsId(rs.getInt("meats_id"));
				purchase.setPickupTime(rs.getTimestamp("pickup_time"));
				purchase.setPurchasedAt(rs.getTimestamp("purchased_at"));
			}

		}
		return purchase;
	}

	@Override
	public void insert(Purchase purchase) throws Exception {
		// Nullチェック
		if (purchase.getUsersId() == null || purchase.getProductsId() == null ||
				purchase.getMeatsId() == null || purchase.getPickupTime() == null) {
			throw new IllegalArgumentException("Invalid purchase data: " + purchase);
		}
		String sql = "INSERT INTO purchase (users_id, products_id, meats_id, pickup_time, purchased_at) VALUES (?, ?, ?, ?, now())";

		try (Connection con = ds.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, purchase.getUsersId());
			stmt.setInt(2, purchase.getProductsId());
			stmt.setInt(3, purchase.getMeatsId());
			stmt.setObject(4, purchase.getPickupTime());
			stmt.executeUpdate();

			//生成されたIDを取得
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				purchase.setId(rs.getInt(1));// IDをセット
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void update(Purchase purchase) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void delete(Purchase purchase) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
