package dao;

import java.util.List;

import domain.Products;

public interface ProductsDao {

	List<Products> findAll()throws Exception;
	Products findById(Integer id) throws Exception;
	void insert(Products product)throws Exception;
	void update(Products product)throws Exception;
	void delete(Products product)throws Exception;
	
}
