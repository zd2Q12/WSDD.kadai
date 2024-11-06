package dao;

import java.util.List;

import domain.Meats;

public interface MeatsDao {
  
	List<Meats> findAll()throws Exception;
	Meats findById(Integer id) throws Exception;
	void insert(Meats meat)throws Exception;
	void update(Meats meat)throws Exception;
	void delete(Meats meat)throws Exception;
}
