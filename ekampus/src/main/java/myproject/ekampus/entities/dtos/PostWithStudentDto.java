package myproject.ekampus.entities.dtos;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostWithStudentDto {
	
	private int id;
	private int studentId; //eklendi
	private String comment;
	private String postPhotoPath;
	private LocalDateTime loadDate = LocalDateTime.now();	
	private String firstName;
	private String lastName;
	private String studentPhotoPath;
}
