package myproject.ekampus.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllFriendshipByStudentNumber {

	private String id;
	private String senderStudentNumber;
	private String receiverStudentNumber;
	private boolean isAccept;
}
