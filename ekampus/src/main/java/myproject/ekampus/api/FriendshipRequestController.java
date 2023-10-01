package myproject.ekampus.api;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import myproject.ekampus.business.abstracts.FriendshipRequestService;
import myproject.ekampus.business.dtos.requests.AcceptFriendshipRequest;
import myproject.ekampus.business.dtos.requests.CreateSendFriendshipRequest;
import myproject.ekampus.business.dtos.requests.DeleteFriendshipRequest;
import myproject.ekampus.business.dtos.requests.RejectFriendshipRequest;
import myproject.ekampus.business.dtos.responses.GetAllFriendshipByStudentNumber;
import myproject.ekampus.business.dtos.responses.GetAllFriendshipRequestByStudentNumber;
import myproject.ekampus.business.dtos.responses.GetAllMySendRequestByStudentNumber;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;

@RestController
@RequestMapping("/api/friendship")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FriendshipRequestController {

	private FriendshipRequestService friendshipRequestService;

	@PostMapping("/sendRequest")
	public Result sendFriendshipRequest(@RequestBody CreateSendFriendshipRequest createSendFriendshipRequest) {
		return this.friendshipRequestService.sendFriendshipRequest(createSendFriendshipRequest);
	}

	@GetMapping("/getActiveRequests")
	public DataResult<List<GetAllFriendshipRequestByStudentNumber>> getAllRequestsByStudentNumber(
			@RequestParam String studentNumber) {
		return this.friendshipRequestService.getAllRequestsByStudentNumber(studentNumber);
	}

	@DeleteMapping("/pullBackRequest")
	public Result deleteFriendshipResultBySender(@RequestBody DeleteFriendshipRequest deleteFriendshipRequest) {
		return this.friendshipRequestService.deleteFriendshipResultBySender(deleteFriendshipRequest);
	}

	@PutMapping("/acceptRequest")
	@CacheEvict(value = "posts", key = "2", condition = "#result.success != false")
	public Result acceptFriendshipRequest(@RequestBody AcceptFriendshipRequest acceptFriendshipRequest) {
		return this.friendshipRequestService.acceptFriendshipRequest(acceptFriendshipRequest);
	}

	@DeleteMapping("/rejectFriendship")
	public Result rejectFriendshipRequest(@RequestBody RejectFriendshipRequest rejectFriendshipRequest) {
		return this.friendshipRequestService.rejectFriendshipRequest(rejectFriendshipRequest);
	}

	@DeleteMapping("/removeFriendship")
	@CacheEvict(value = "posts", key = "2", condition = "#result.success != false")
	public Result removeFriendship(@RequestParam String entryStudentNumber,@RequestParam String studentNumber) {
		return this.friendshipRequestService.removeFriendship(entryStudentNumber, studentNumber);
	}

	@GetMapping("/getFriendshipsByStudentNumber")
	public DataResult<List<GetAllFriendshipByStudentNumber>> getAllFriendshipByStudentNumber(@RequestParam String studentNumber) {
		return this.friendshipRequestService.getAllFriendshipByStudentNumber(studentNumber);
	}

	@GetMapping("/getMySendRequests")
	public DataResult<List<GetAllMySendRequestByStudentNumber>> getAllMySendFriendship(@RequestParam String studentNumber) {
		return this.friendshipRequestService.getAllMySendFriendship(studentNumber);
	}

	@GetMapping("/areWeFriends")
	public DataResult<Integer> areWeFriends(@RequestParam String studentNumber,@RequestParam String otherStudentNumber) {
		return this.friendshipRequestService.areWeFriends(studentNumber, otherStudentNumber);
	}

}
