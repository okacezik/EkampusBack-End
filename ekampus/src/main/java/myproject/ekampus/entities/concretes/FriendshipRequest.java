package myproject.ekampus.entities.concretes;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "friendship_requests")
public class FriendshipRequest {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "sender_student_number")
	private String senderStudentNumber;

	@Column(name = "receiver_student_number")
	private String receiverStudentNumber;

	@Column(name = "send_date")
	private LocalDateTime senderDate = LocalDateTime.now();

	@Column(name = "is_accept")
	private boolean isAccept;
}
