package myproject.ekampus.business.dtos.responses;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikedPost implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int postId;
	private String postComment;
	private LocalDateTime postLoadDate;
	private int postStudentId;
	private String postStudentFirstName;
	private String postStudentLastName;
	private String postStudentStudentNumber;
}
