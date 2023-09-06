package myproject.ekampus.business.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteFriendshipRequest {

	private String senderStudentNumber;
	private String receiverStudentNumber;

}
