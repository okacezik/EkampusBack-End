package myproject.ekampus.business.dtos.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllFriendshipRequestByStudentNumber {
	
	private String senderStudentNumber;
	private LocalDateTime senderDate;

}
