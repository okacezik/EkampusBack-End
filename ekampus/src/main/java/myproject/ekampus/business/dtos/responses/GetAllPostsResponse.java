package myproject.ekampus.business.dtos.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllPostsResponse {

	private int studentId; 
	private String comment;
	private String postPhotoPath;
	private String firstName;
	private String lastName;
	private String studentPhotoPath;
	private LocalDateTime loadDate = LocalDateTime.now();	

}
