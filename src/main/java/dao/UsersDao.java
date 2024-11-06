package dao;

import java.util.List;

import domain.Users;

public interface UsersDao {

	List<Users> findAll()throws Exception;
	Users findById(Integer id)throws Exception;
	void insert(Users users)throws Exception;
	void update(Users users)throws Exception;
	void delete(Users users)throws Exception;
	
	Users findByLoginIdAndLoginPass(String loginId, String loginPass)throws Exception;
}
