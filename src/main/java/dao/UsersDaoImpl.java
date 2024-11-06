package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;

import domain.Users;

public class UsersDaoImpl implements UsersDao {
	private DataSource ds;

	public UsersDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Users> findAll() throws Exception {
		List<Users> userList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "select * from users";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				userList.add(mapToUsers(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return userList;
	}

	private Users mapToUsers(ResultSet rs) throws Exception {
		Users user = new Users();
		user.setId((Integer) rs.getObject("id"));
		user.setName(rs.getString("name"));
		user.setTel(rs.getString("tel"));
		user.setEmail(rs.getString("email"));
		user.setLoginId(rs.getString("login_id"));
		user.setLoginPass(rs.getString("login_pass"));

		return user;
	}

	@Override
	public Users findById(Integer id) throws Exception {
		Users users = new Users();

		try (Connection con = ds.getConnection()) {
			String sql = "select * from users"
					+ " where users.id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next() == true) {
				users = mapToUsers(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return users;
	}

	@Override
	public void insert(Users user) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO users (name, tel, email, login_id, login_pass) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getTel());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getLoginId());
			stmt.setString(5, user.getLoginPass());
			stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(Users users) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "update users set name = ?, tel = ?, email = ?, login_id = ?, login_pass = ?) where id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, users.getName());
			stmt.setString(2, users.getTel());
			stmt.setString(3, users.getEmail());
			stmt.setString(4, users.getLoginId());
			stmt.setString(5, users.getLoginPass());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(Users users) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "delete from users where id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, users.getId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Users findByLoginIdAndLoginPass(String loginId, String loginPass) throws Exception {
		Users user = null;
		try (Connection con = ds.getConnection()) {
			String sql = "select * from users where login_id= ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, loginId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (BCrypt.checkpw(loginPass, rs.getString("login_pass"))) {
					user = mapToUsers(rs);
				}
			}

		} catch (Exception e) {
			throw e;
		}
		return user;
	}

}
