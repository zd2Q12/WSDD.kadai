package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import domain.PurchaseHistory;

public class PurchaseHistoryDaoImpl implements PurchaseHistoryDao {

	private DataSource ds;

	public PurchaseHistoryDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<PurchaseHistory> findById(Integer userId) throws Exception {
		List<PurchaseHistory> purchaseHistories = new ArrayList<>();
		String sql = " select p.*,"
				+ " u.name AS users_name, pr.name AS products_name, pr.price AS product_price, m.name AS meats_name,"
				+ " GROUP_CONCAT(DISTINCT v.name) AS vegetables_name,"
				+ " GROUP_CONCAT(DISTINCT v.id) AS vegetable_ids,"
				+ " GROUP_CONCAT(DISTINCT s.name) AS sauce_name,"
				+ " GROUP_CONCAT(DISTINCT s.id) AS sauce_ids FROM purchase p"
				+ " LEFT JOIN users u ON p.users_id = u.id"
				+ " LEFT JOIN products pr ON p.products_id = pr.id"
				+ " LEFT JOIN meats m ON p.meats_id = m.id"
				+ " LEFT JOIN purchase_vegetables pv ON p.id = pv.purchase_id"
				+ " LEFT JOIN vegetables v ON pv.vegetables_id = v.id"
				+ " LEFT JOIN purchase_sauces ps ON p.id = ps.purchase_id"
				+ " LEFT JOIN sauce s ON ps.sauce_id = s.id"
				+ " WHERE p.users_id = ?"
				+ " GROUP BY p.id, u.name, pr.name, m.name"
				+ " ORDER BY purchased_at DESC";

		try (Connection con = ds.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PurchaseHistory purchaseHistory = new PurchaseHistory();
				purchaseHistory.setUsersId(rs.getInt("users_id"));
				purchaseHistory.setUserName(rs.getString("users_name")); // ユーザー名を設定
		    purchaseHistory.setProductsId(rs.getInt("products_id")); // ここでproducts_idを設定
				purchaseHistory.setProductName(rs.getString("products_name")); // 商品名を設定
				purchaseHistory.setProductPrice(rs.getInt("product_price")); 
				purchaseHistory.setMeatsId(rs.getInt("meats_id"));
				purchaseHistory.setMeatName(rs.getString("meats_name")); // 肉名を設定
				//purchaseHistory.setPickupTime(rs.getTimestamp("pickup_time"));
				purchaseHistory.setPurchasedAt(rs.getTimestamp("purchased_at"));

				// 名前を取得してリストに変換
				String vegetableName = rs.getString("vegetables_name");
				//System.out.println("Vegetable Name: " + vegetableName); // デバッグ用
				if (vegetableName != null) {
					String[] vegName = vegetableName.split(",");
					purchaseHistory.setVegetableName(new ArrayList<>(Arrays.asList(vegName)));
				} else {
					purchaseHistory.setVegetableName(new ArrayList<>());
				}
				String vegetableIds = rs.getString("vegetable_ids");
				if (vegetableIds != null) {
					String[] vegeId = vegetableIds.split(",");
					List<Integer> vegeIdList = new ArrayList<>();
					for (String id : vegeId) {
						vegeIdList.add(Integer.valueOf(id));
					}
					purchaseHistory.setVegetableIds(vegeIdList); // IDを設定
				} else {
					purchaseHistory.setVegetableIds(new ArrayList<>());
				}

				String sauceName = rs.getString("sauce_name");
				//System.out.println("Sauce Name: " + sauceName); // デバッグ用
				if (sauceName != null) {
					String[] sauName = sauceName.split(",");
					purchaseHistory.setSauceName(new ArrayList<>(Arrays.asList(sauName)));
				} else {
					purchaseHistory.setSauceName(new ArrayList<>());
				}

				String sauceIds = rs.getString("sauce_ids");
				if (sauceIds != null) {
					String[] sauId = sauceIds.split(",");
					List<Integer> sauIdList = new ArrayList<>();
					for (String id : sauId) {
						sauIdList.add(Integer.valueOf(id));
					}
					purchaseHistory.setSauceIds(sauIdList); // IDを設定
				} else {
					purchaseHistory.setSauceIds(new ArrayList<>());
				}

				purchaseHistories.add(purchaseHistory);
			}
		}
		return purchaseHistories;
	}

}
