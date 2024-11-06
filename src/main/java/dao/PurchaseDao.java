package dao;

import java.util.List;

import domain.Purchase;

public interface PurchaseDao {

	List<Purchase> findAll()throws Exception;
	Purchase findById(Integer id)throws Exception;	
	void insert(Purchase purchase)throws Exception;
	void update(Purchase purchase)throws Exception;
	void delete(Purchase purchase)throws Exception;
	}
