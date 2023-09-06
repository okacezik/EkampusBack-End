package myproject.ekampus.business.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSendFriendshipRequest {

	private String senderStudentNumber;
	private String receiverStudentNumber;

}
