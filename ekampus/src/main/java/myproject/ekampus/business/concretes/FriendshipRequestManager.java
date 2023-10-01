package myproject.ekampus.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import myproject.ekampus.business.abstracts.FriendshipRequestService;
import myproject.ekampus.business.dtos.requests.AcceptFriendshipRequest;
import myproject.ekampus.business.dtos.requests.CreateSendFriendshipRequest;
import myproject.ekampus.business.dtos.requests.DeleteFriendshipRequest;
import myproject.ekampus.business.dtos.requests.RejectFriendshipRequest;
import myproject.ekampus.business.dtos.responses.GetAllFriendshipByStudentNumber;
import myproject.ekampus.business.dtos.responses.GetAllFriendshipRequestByStudentNumber;
import myproject.ekampus.business.dtos.responses.GetAllMySendRequestByStudentNumber;
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

		FriendshipRequest request = this.friendshipRequestDao
				.findById(createSendFriendshipRequest.getSenderStudentNumber()
						+ createSendFriendshipRequest.getReceiverStudentNumber());

		if (request == null) {
			FriendshipRequest newRequest = this.modelMapperService.forRequest().map(createSendFriendshipRequest,
					FriendshipRequest.class);

			newRequest.setId(newRequest.getSenderStudentNumber() + newRequest.getReceiverStudentNumber());
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

		Optional<FriendshipRequest> request = this.friendshipRequestDao.findByReceiverStudentNumberAndSenderStudentNumber(
				deleteFriendshipRequest.getReceiverStudentNumber(), deleteFriendshipRequest.getSenderStudentNumber());

		this.friendshipRequestDao.delete(request.get());
		return new SuccessResult(Messages.requestsDeleted);
	}

	@Override
	public Result acceptFriendshipRequest(AcceptFriendshipRequest acceptFriendshipRequest) {
		Optional<FriendshipRequest> request = this.friendshipRequestDao.findByReceiverStudentNumberAndSenderStudentNumber(
				acceptFriendshipRequest.getReceiverStudentNumber(), acceptFriendshipRequest.getSenderStudentNumber());
		request.get().setAccept(true);
		this.friendshipRequestDao.save(request.get());
		return new SuccessResult(Messages.requestAccepted);
	}

	@Override
	public Result rejectFriendshipRequest(RejectFriendshipRequest rejectFriendshipRequest) {
		Optional<FriendshipRequest> request = this.friendshipRequestDao.findByReceiverStudentNumberAndSenderStudentNumber(
				rejectFriendshipRequest.getReceiverStudentNumber(), rejectFriendshipRequest.getSenderStudentNumber());
		if(request.isPresent()) {
			this.friendshipRequestDao.delete(request.get());
			return new SuccessResult(Messages.requestsDeleted);
		}

		return new ErrorResult("Not found friendship");
	}

	@Override
	public DataResult<List<GetAllFriendshipByStudentNumber>> getAllFriendshipByStudentNumber(String studentNumber) {
		List<FriendshipRequest> friendships = this.friendshipRequestDao.findByIdContains(studentNumber);

		List<FriendshipRequest> filteredFriendships = friendships.stream().filter(friendship -> friendship.isAccept())
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllFriendshipByStudentNumber>>(
				filteredFriendships.stream()
						.map(friendship -> this.modelMapperService.forResponse().map(friendship,
								GetAllFriendshipByStudentNumber.class))
						.collect(Collectors.toList()),
				Messages.requestsListed);

	}

	@Override
	public Result removeFriendship(String entryStudentNumber, String studentNumber) {
		FriendshipRequest friendship = this.friendshipRequestDao.findById(entryStudentNumber + studentNumber);
		if (friendship == null) {
			friendship = this.friendshipRequestDao.findById(studentNumber + entryStudentNumber);
			if (friendship != null) {
				FriendshipRequest currentFriendship = this.modelMapperService.forRequest().map(friendship,
						FriendshipRequest.class);
				this.friendshipRequestDao.delete(currentFriendship);
				return new SuccessResult(Messages.requestsDeleted);
			} else {
				return new ErrorResult(Messages.notRequestDeleted);
			}

		}
		FriendshipRequest currentFriendship = this.modelMapperService.forRequest().map(friendship,
				FriendshipRequest.class);
		this.friendshipRequestDao.delete(currentFriendship);
		return new SuccessResult(Messages.requestsDeleted);
	}

	@Override
	public DataResult<List<GetAllMySendRequestByStudentNumber>> getAllMySendFriendship(String studentNumber) {
		List<FriendshipRequest> requests = this.friendshipRequestDao.findBySenderStudentNumber(studentNumber);
		requests = requests.stream().filter(request -> !request.isAccept()).collect(Collectors.toList());
		List<GetAllMySendRequestByStudentNumber> mySendRequests = requests.stream()
				.map(request -> this.modelMapperService.forResponse().map(request,
						GetAllMySendRequestByStudentNumber.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllMySendRequestByStudentNumber>>(mySendRequests,
				Messages.requestsListed);
	}

	@Override
	public DataResult<Integer> areWeFriends(String studentNumber, String otherStudentNumber) {
		FriendshipRequest friendshipRequest = this.friendshipRequestDao.findById(otherStudentNumber+studentNumber);
		if(friendshipRequest != null) {
			if (friendshipRequest.isAccept()) {
				return new SuccessDataResult<Integer>(1, "We are friends");
			}else {
				return new SuccessDataResult<Integer>(2, "We are not friends, he/she sended request me");
			}
		}
		friendshipRequest = this.friendshipRequestDao.findById(studentNumber+otherStudentNumber);
		if(friendshipRequest != null) {
			if (friendshipRequest.isAccept()) {
				return new SuccessDataResult<Integer>(1, "We are friends");
			}else {
				return new SuccessDataResult<Integer>(3, "We are not friends, I sended request");
			}
		}
		
		return new SuccessDataResult<Integer>(4, "We are not friends, be friends");
	}


}
