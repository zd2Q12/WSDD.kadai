package dao;

import java.util.List;

import domain.Vegetables;

public interface VegetablesDao {

	List<Vegetables> findAll()throws Exception;
	Vegetables findById(Integer id)throws Exception;
	void insert(Vegetables vegetable)throws Exception;
	void update(Vegetables vegetable)throws Exception;
	void delete(Vegetables vegetable)throws Exception;
}
