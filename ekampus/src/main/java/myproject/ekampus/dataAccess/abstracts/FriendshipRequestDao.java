package myproject.ekampus.dataAccess.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import myproject.ekampus.entities.concretes.FriendshipRequest;

public interface FriendshipRequestDao extends JpaRepository<FriendshipRequest, Integer> {

	List<FriendshipRequest> findByReceiverStudentNumber(String studentNumber);

	Optional<FriendshipRequest> findByReceiverStudentNumberAndSenderStudentNumber(String receiverStudentNumber,
			String senderStudentNumber);
	
	FriendshipRequest findById(String id);
	
	List<FriendshipRequest> findByIdContains(String studentNumber);
	
	List<FriendshipRequest> findBySenderStudentNumber(String studentNumber);
}
