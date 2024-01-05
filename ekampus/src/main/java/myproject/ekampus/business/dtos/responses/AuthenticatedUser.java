package myproject.ekampus.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatedUser {

	private int id;
	private String studentNumber;
	private String departmantName;
	private String firstName;
	private String lastName;
	private boolean hiddenAccount;
	private String token;
}
