package myproject.ekampus.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetLikeByPostId {

	int postId;
	int likeStudentId;
	String likeStudentFirstName;
	String likeStudentLastName;
}
