package domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseVegetables {
  private Integer purchaseId;
  private List<Integer> vegetablesId;//*
  
  public void setVegetablesId(List<Integer> vegetablesId) {
  	this.vegetablesId = vegetablesId;
  }
}
