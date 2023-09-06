package myproject.ekampus.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import myproject.ekampus.business.abstracts.FriendshipRequestService;
import myproject.ekampus.business.dtos.requests.AcceptFriendshipRequest;
import myproject.ekampus.business.dtos.requests.CreateSendFriendshipRequest;
import myproject.ekampus.business.dtos.requests.DeleteFriendshipRequest;
import myproject.ekampus.business.dtos.requests.RejectFriendshipRequest;
import myproject.ekampus.business.dtos.responses.GetAllFriendshipRequestByStudentNumber;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;

@RestController
@RequestMapping("/api/friendship")
@AllArgsConstructor
public class FriendshipRequestController {

	private FriendshipRequestService friendshipRequestService;
	
	@PostMapping()
	public Result sendFriendshipRequest(CreateSendFriendshipRequest createSendFriendshipRequest) {
		return this.friendshipRequestService.sendFriendshipRequest(createSendFriendshipRequest);
	}
	
	@GetMapping()
	public DataResult<List<GetAllFriendshipRequestByStudentNumber>> getAllRequestsByStudentNumber(String studentNumber){
		return this.friendshipRequestService.getAllRequestsByStudentNumber(studentNumber);
	}
	
	@DeleteMapping("/pullbackrequest")
	public Result deleteFriendshipResultBySender(DeleteFriendshipRequest deleteFriendshipRequest) {
		return this.friendshipRequestService.deleteFriendshipResultBySender(deleteFriendshipRequest);
	}
	
	@PutMapping()
	public Result acceptFriendshipRequest(AcceptFriendshipRequest acceptFriendshipRequest) {
		return this.friendshipRequestService.acceptFriendshipRequest(acceptFriendshipRequest);
	}
	
	@DeleteMapping("/rejectfriendship")
	public Result rejectFriendshipRequest(RejectFriendshipRequest rejectFriendshipRequest) {
		return this.friendshipRequestService.rejectFriendshipRequest(rejectFriendshipRequest);
	}
 
}
