package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Sauce;

public class SauceDaoImpl implements SauceDao {
	private DataSource ds;

	public SauceDaoImpl(DataSource ds){
		this.ds = ds;
	}

	@Override
	public List<Sauce> findAll() throws Exception {
		List<Sauce> sauceList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "select id, name, description from Sauce";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				sauceList.add(mapToSauce(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return sauceList;
	}

	private Sauce mapToSauce(ResultSet rs) throws Exception {
		Sauce sauce = new Sauce();
		sauce.setId((Integer) rs.getObject("id"));
		sauce.setName(rs.getString("name"));
		sauce.setDescription(rs.getString("description"));
		return sauce;
	}

	@Override
	public Sauce findById(Integer id)throws Exception{
		Sauce sauce = null;
		try(Connection con = ds.getConnection()){
			String sql = "select id, name, description from sauce where id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				sauce = mapToSauce(rs);
			}
		}catch(Exception e) {
			throw e;
		}
		return sauce;
	}

	@Override
	public void insert(Sauce sauce) throws Exception {
		try(Connection con = ds.getConnection()){
			String sql ="insert into sauce(name, description) values(?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, sauce.getName());
			stmt.setString(2, sauce.getDescription());
			stmt.executeUpdate();
		} catch(Exception e) {
			throw e;
		}

	}

	@Override
	public void update(Sauce sauce) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void delete(Sauce sauce) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
