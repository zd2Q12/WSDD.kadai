package domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Purchase {
	private Integer id;
	private Integer usersId;
	private Integer productsId;
	private Integer meatsId;
	private Date pickupTime;
	private Date purchasedAt;

	
	private String name;
	private String tel;
	private String email;
}
