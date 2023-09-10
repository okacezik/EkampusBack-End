package myproject.ekampus.business.dtos.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllMySendRequestByStudentNumber {

	private String receiverStudentNumber;
	private LocalDateTime senderDate;
	private boolean isAccept;

}
