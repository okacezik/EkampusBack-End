package myproject.ekampus.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetailDto {

	private int studentId;
	private String studentNumber;
	private String departmantName;
	private String firstName;
	private String lastName;
	private String password;
	private String studentPhotoPath;
}
