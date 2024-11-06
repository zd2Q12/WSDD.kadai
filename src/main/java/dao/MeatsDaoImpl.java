package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Meats;

public class MeatsDaoImpl implements MeatsDao {
	private DataSource ds;

	public MeatsDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Meats> findAll() throws Exception {
		List<Meats> meatList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "select id, name, description from meats";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				meatList.add(mapToMeats(rs));
			}
		} catch (Exception e) {
			throw e;
		}

		return meatList;
	}

	private Meats mapToMeats(ResultSet rs) throws Exception {
		Meats meats = new Meats();
		meats.setId((Integer) rs.getObject("id"));
		meats.setName(rs.getString("name"));
		meats.setDescription(rs.getString("description"));
		return meats;
	}

	@Override
	public Meats findById(Integer id) throws Exception {
		Meats meat = null;
		try (Connection con = ds.getConnection()) {
			String sql = "select id, name, description from meats where id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				meat = mapToMeats(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return meat;
	}

	@Override
	public void insert(Meats meat) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "insert into meats(name, description)values(?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, meat.getName());
			stmt.setString(2, meat.getDescription());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(Meats meat) throws Exception {
	
	}

	@Override
	public void delete(Meats meat) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
