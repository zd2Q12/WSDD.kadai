package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.PurchaseVegetables;

public class PurchaseVegetablesDaoImpl implements PurchaseVegetablesDao {

	private DataSource ds;

	public PurchaseVegetablesDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<PurchaseVegetables> findAll() throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public PurchaseVegetables findById(Integer id) throws Exception {
		PurchaseVegetables purchaseVege = null;
		String sql = "select purchase_id, vegetables_id"
				+" from purchase, vegetables where purchase_id= ?, vegetables_id = ?";
		
		try(Connection con = ds.getConnection()){
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				purchaseVege = new PurchaseVegetables();
				purchaseVege.setPurchaseId(rs.getInt("purchase_id"));
				
				List<Integer> vegetablesList = new ArrayList<>();//List初期化
				do {
					vegetablesList.add(rs.getInt("vegetables_id"));// vegetables_idをListに追加
				}while(rs.next());
					purchaseVege.setVegetablesId(vegetablesList);
				}
			}
		return purchaseVege;
	}
	

	@Override
	public void insert(PurchaseVegetables purchaseVegetables) throws Exception {
		String sql = "INSERT INTO purchase_vegetables (purchase_id, vegetables_id) VALUES (?, ?)";

		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, purchaseVegetables.getPurchaseId());
			for(Integer vegetableId : purchaseVegetables.getVegetablesId()) {
				stmt.setInt(2, vegetableId);
				stmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
