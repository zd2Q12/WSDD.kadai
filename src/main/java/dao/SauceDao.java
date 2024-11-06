package dao;

import java.util.List;

import domain.Sauce;

public interface SauceDao {

	List<Sauce> findAll()throws Exception;
  Sauce findById(Integer id) throws Exception;
	void insert(Sauce sauce)throws Exception;
	void update(Sauce sauce)throws Exception;
	void delete(Sauce sauce)throws Exception;
	
}
