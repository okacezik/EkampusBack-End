package myproject.ekampus.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import myproject.ekampus.entities.concretes.FriendshipRequest;

public interface FriendshipRequestDao extends JpaRepository<FriendshipRequest, Integer> {

	List<FriendshipRequest> findByReceiverStudentNumber(String studentNumber);

	FriendshipRequest findByReceiverStudentNumberAndSenderStudentNumber(String receiverStudentNumber,
			String senderStudentNumber);
}
