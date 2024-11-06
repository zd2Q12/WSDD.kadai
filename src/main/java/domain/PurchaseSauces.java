package domain;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseSauces {
 private Integer purchaseId;
 private List<Integer> sauceId;
 
 public void setSauceId(List<Integer> sauceId) {
	 this.sauceId = sauceId;
 }
}
