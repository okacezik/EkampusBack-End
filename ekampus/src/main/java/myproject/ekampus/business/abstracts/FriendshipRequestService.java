package myproject.ekampus.business.abstracts;

import java.util.List;

import myproject.ekampus.business.dtos.requests.AcceptFriendshipRequest;
import myproject.ekampus.business.dtos.requests.CreateSendFriendshipRequest;
import myproject.ekampus.business.dtos.requests.DeleteFriendshipRequest;
import myproject.ekampus.business.dtos.requests.RejectFriendshipRequest;
import myproject.ekampus.business.dtos.responses.GetAllFriendshipRequestByStudentNumber;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;

public interface FriendshipRequestService {

	Result sendFriendshipRequest(CreateSendFriendshipRequest createSendFriendshipRequest);
	
	Result deleteFriendshipResultBySender(DeleteFriendshipRequest deleteFriendshipRequest);
	
	Result acceptFriendshipRequest(AcceptFriendshipRequest acceptFriendshipRequest);
	
	Result rejectFriendshipRequest(RejectFriendshipRequest rejectFriendshipRequest);
	
	DataResult<List<GetAllFriendshipRequestByStudentNumber>> getAllRequestsByStudentNumber(String studentNumber);
}
