package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Vegetables;

public class VegetablesDaoImpl implements VegetablesDao {
	private DataSource ds;

	public VegetablesDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Vegetables> findAll() throws Exception {
		List<Vegetables> veggiesList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "select id, name, description from vegetables";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				veggiesList.add(mapToVegetables(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return veggiesList;
	}

	private Vegetables mapToVegetables(ResultSet rs) throws Exception {
		Vegetables veggies = new Vegetables();
		veggies.setId((Integer) rs.getObject("id"));
		veggies.setName(rs.getString("name"));
		veggies.setDescription(rs.getString("description"));
		return veggies;
	}

	@Override
	public Vegetables findById(Integer id) throws Exception {
		Vegetables veggies = null;
		try (Connection con = ds.getConnection()) {
			String sql = "select id, name, description from vegetables where id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				veggies = mapToVegetables(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return veggies;
	}

	@Override
	public void insert(Vegetables veggies) throws Exception {
		try(Connection con = ds.getConnection()){
			String sql = "insert into vegetables(name, description)values(?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, veggies.getName());
			stmt.setString(2, veggies.getDescription());
			stmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}

	}

	@Override
	public void update(Vegetables vegetable) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void delete(Vegetables vegetable) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
