package myproject.ekampus.business.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteFriendshipRequest {

	@NotNull
	@NotBlank
	private String senderStudentNumber;
	
	@NotNull
	@NotBlank
	private String receiverStudentNumber;

}
