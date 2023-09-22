package myproject.ekampus.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdStudentResponse {
	
	private int id;
	private String studentNumber;
	private String departmantName;
	private String firstName;
	private String lastName;
}
