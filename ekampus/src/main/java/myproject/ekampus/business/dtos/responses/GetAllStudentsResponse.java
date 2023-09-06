package myproject.ekampus.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllStudentsResponse {

	private String studentNumber;
	private String departmantName;
	private String firstName;
	private String lastName;
	//private String password;
	private String studentPhotoPath;
}
