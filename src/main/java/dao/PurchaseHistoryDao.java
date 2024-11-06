package dao;

import java.util.List;

import domain.PurchaseHistory;

public interface PurchaseHistoryDao {
 List<PurchaseHistory> findById(Integer userId)throws Exception;
}
