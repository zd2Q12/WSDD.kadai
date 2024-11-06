package domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//2024.10.09
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseHistory {
  //private Integer id;
  private Integer usersId;
  private String userName;
  private Integer productsId;
  private String productName;
  private Integer meatsId;
  private String meatName;
 // private Timestamp pickupTime;
  private Timestamp purchasedAt;
  private List<Integer> vegetableIds; // Listに変更
  private List<String> vegetableName; // Listに変更
  private List<Integer> sauceIds; // Listに変更
  private List<String> sauceName; // Listに変更
  private Integer productPrice;

}
