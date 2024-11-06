package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {

	private Integer id;
	private String name;
	private String tel;
	private String email;
	private String loginId;
	private String loginPass;
}
