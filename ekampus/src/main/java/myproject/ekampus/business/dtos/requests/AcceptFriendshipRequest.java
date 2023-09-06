package myproject.ekampus.business.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcceptFriendshipRequest {

	private String senderStudentNumber;
	private String receiverStudentNumber;
	private boolean isAccept = true;
}
