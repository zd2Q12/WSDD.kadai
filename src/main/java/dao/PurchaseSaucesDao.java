package dao;

import java.util.List;

import domain.PurchaseSauces;

public interface PurchaseSaucesDao {
 List<PurchaseSauces> findAll()throws Exception;
 PurchaseSauces findById(Integer id) throws Exception;
 void insert(PurchaseSauces purchaseSauce)throws Exception;
}
