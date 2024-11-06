package dao;

import java.util.List;

import domain.PurchaseVegetables;

public interface PurchaseVegetablesDao {
	   List<PurchaseVegetables> findAll() throws Exception;
	   PurchaseVegetables findById(Integer id)throws Exception;
	   void insert(PurchaseVegetables purchaseVegetables)throws Exception;
	}


