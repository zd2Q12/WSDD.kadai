package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.PurchaseSauces;

public class PurchaseSaucesDaoImpl implements PurchaseSaucesDao {

	private DataSource ds;

	public PurchaseSaucesDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<PurchaseSauces> findAll() throws Exception {
		return null;
	}

	@Override
	public PurchaseSauces findById(Integer id) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		PurchaseSauces purchaseSauce = null;
		String sql = "select purchase_id, sauce_id"
				+" from purchase, sauce where purchase_id= ?, sauce_id= ?";
		
		try(Connection con = ds.getConnection()){
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				purchaseSauce = new PurchaseSauces();
				purchaseSauce.setPurchaseId(rs.getInt("purchase_id"));
				
				List<Integer> sauceList = new ArrayList<>();
				do {
					sauceList.add(rs.getInt("sauce_id"));//sauce_idをListに追加
				}while(rs.next());
				purchaseSauce.setSauceId(sauceList);
			}
		}
		return purchaseSauce;
	}

	@Override
	public void insert(PurchaseSauces purchaseSauce) throws Exception {
String sql = "insert into purchase_sauces(purchase_id, sauce_id) values(?, ?)";

try(Connection con = ds.getConnection()){
	PreparedStatement stmt = con.prepareStatement(sql);
	stmt.setInt(1, purchaseSauce.getPurchaseId());
	for(Integer sauceId : purchaseSauce.getSauceId()) {
		stmt.setInt(2, sauceId);
		stmt.executeUpdate();
	}
}catch(Exception e) {
	e.printStackTrace();
}
	}

}
