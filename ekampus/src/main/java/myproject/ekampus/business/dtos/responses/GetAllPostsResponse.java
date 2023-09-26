package myproject.ekampus.business.dtos.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllPostsResponse {
	
	private int id;
	private int studentId; 
	private String studentNumber; 
	private String comment;
	private String postPhotoPath;
	private String firstName;
	private String lastName;
	private LocalDateTime loadDate = LocalDateTime.now();	

}
