package myproject.ekampus.business.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikeThePostRequest {

	@NotNull
	@NotBlank
	private int likeStudentId;
	
	@NotNull
	@NotBlank
	private int postId;
}
