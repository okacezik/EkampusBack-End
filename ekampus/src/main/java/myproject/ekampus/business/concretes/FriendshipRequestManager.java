package myproject.ekampus.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import myproject.ekampus.business.abstracts.FriendshipRequestService;
import myproject.ekampus.business.dtos.requests.AcceptFriendshipRequest;
import myproject.ekampus.business.dtos.requests.CreateSendFriendshipRequest;
import myproject.ekampus.business.dtos.requests.DeleteFriendshipRequest;
import myproject.ekampus.business.dtos.requests.RejectFriendshipRequest;
import myproject.ekampus.business.dtos.responses.GetAllFriendshipRequestByStudentNumber;
import myproject.ekampus.core.utilites.mappers.ModelMapperService;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.ErrorResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.core.utilites.results.SuccessDataResult;
import myproject.ekampus.core.utilites.results.SuccessResult;
import myproject.ekampus.dataAccess.abstracts.FriendshipRequestDao;
import myproject.ekampus.entities.concretes.FriendshipRequest;

@AllArgsConstructor
@Service
public class FriendshipRequestManager implements FriendshipRequestService {

	private FriendshipRequestDao friendshipRequestDao;
	private ModelMapperService modelMapperService;

	@Override
	public Result sendFriendshipRequest(CreateSendFriendshipRequest createSendFriendshipRequest) {

		FriendshipRequest request = this.friendshipRequestDao.findByReceiverStudentNumberAndSenderStudentNumber(
				createSendFriendshipRequest.getReceiverStudentNumber(),
				createSendFriendshipRequest.getSenderStudentNumber());

		if (request == null) {
			FriendshipRequest newRequest = this.modelMapperService.forRequest().map(createSendFriendshipRequest,
					FriendshipRequest.class);
			this.friendshipRequestDao.save(newRequest);
			return new SuccessResult(Messages.requestSend);
		}

		return new ErrorResult(Messages.notRequestSend);
	}

	@Override
	public DataResult<List<GetAllFriendshipRequestByStudentNumber>> getAllRequestsByStudentNumber(
			String studentNumber) {

		// active requests
		List<FriendshipRequest> requests = this.friendshipRequestDao.findByReceiverStudentNumber(studentNumber);
		requests = requests.stream().filter(request -> request.isAccept() == false).collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllFriendshipRequestByStudentNumber>>(
				requests.stream()
						.map(request -> this.modelMapperService.forResponse().map(request,
								GetAllFriendshipRequestByStudentNumber.class))
						.collect(Collectors.toList()),
				Messages.requestsListed);
	}

	@Override
	public Result deleteFriendshipResultBySender(DeleteFriendshipRequest deleteFriendshipRequest) {

		FriendshipRequest request = this.friendshipRequestDao.findByReceiverStudentNumberAndSenderStudentNumber(
				deleteFriendshipRequest.getReceiverStudentNumber(), deleteFriendshipRequest.getSenderStudentNumber());

		this.friendshipRequestDao.delete(request);
		return new SuccessResult(Messages.requestsDeleted);
	}

	@Override
	public Result acceptFriendshipRequest(AcceptFriendshipRequest acceptFriendshipRequest) {
		FriendshipRequest request = this.friendshipRequestDao.findByReceiverStudentNumberAndSenderStudentNumber(
				acceptFriendshipRequest.getReceiverStudentNumber(), acceptFriendshipRequest.getSenderStudentNumber());
		request.setAccept(true);
		this.friendshipRequestDao.save(request);
		return new SuccessResult(Messages.requestAccepted);
	}

	@Override
	public Result rejectFriendshipRequest(RejectFriendshipRequest rejectFriendshipRequest) {
		FriendshipRequest request = this.friendshipRequestDao.findByReceiverStudentNumberAndSenderStudentNumber(
				rejectFriendshipRequest.getReceiverStudentNumber(), rejectFriendshipRequest.getSenderStudentNumber());
		this.friendshipRequestDao.delete(request);
		return new SuccessResult(Messages.requestsDeleted);
	}

}
