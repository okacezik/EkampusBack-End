package myproject.ekampus.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import myproject.ekampus.business.abstracts.FriendshipRequestService;
import myproject.ekampus.business.abstracts.PostService;
import myproject.ekampus.business.dtos.requests.CreatePostRequest;
import myproject.ekampus.business.dtos.responses.GetAllFriendshipByStudentNumber;
import myproject.ekampus.business.dtos.responses.GetAllPostsResponse;
import myproject.ekampus.core.utilites.mappers.ModelMapperService;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.ErrorDataResult;
import myproject.ekampus.core.utilites.results.ErrorResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.core.utilites.results.SuccessDataResult;
import myproject.ekampus.core.utilites.results.SuccessResult;
import myproject.ekampus.dataAccess.abstracts.PostDao;
import myproject.ekampus.entities.concretes.Post;

@Service
@AllArgsConstructor
public class PostManager implements PostService {

	private PostDao postDao;
	private ModelMapperService mapperService;
	private FriendshipRequestService friendshipRequestService;

	@Override
	public DataResult<List<GetAllPostsResponse>> add(CreatePostRequest createPostRequest) {
		Post post = this.mapperService.forRequest().map(createPostRequest, Post.class);
		this.postDao.save(post);

		return this.getPostWithStudentDetailsSortedByLoadDate();
	}

	@Override
	public Result delete(int postId) {

		Optional<Post> post = this.postDao.findById(postId);

		if (post.isPresent()) {
			this.postDao.deleteById(postId);
			return new SuccessResult(Messages.postDeleteMessage);
		}
		return new ErrorResult(Messages.notFindPost);
	}

	@Override
	public DataResult<List<GetAllPostsResponse>> getPostWithStudentDetailsSortedByLoadDate() {

		Sort sort = Sort.by(Direction.DESC, "loadDate");
		List<Post> posts = this.postDao.findAll(sort);
		List<GetAllPostsResponse> response = posts.stream()
				.map(post -> this.mapperService.forResponse().map(post, GetAllPostsResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllPostsResponse>>(response, Messages.postsListMessage);
	}

	// discovery
	@Override
	public DataResult<List<GetAllPostsResponse>> getAllPostsWithStudentDetails() {
		List<Post> posts = this.postDao.findAll();
		List<Post> filteredPosts = posts.stream().filter(post -> !post.getStudent().isHiddenAccount())
				.collect(Collectors.toList());
		List<GetAllPostsResponse> response = filteredPosts.stream()
				.map(post -> this.mapperService.forResponse().map(post, GetAllPostsResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllPostsResponse>>(response, Messages.postsListMessage);
	}

	@Override
	public DataResult<List<GetAllPostsResponse>> findByStudentNumberPosts(String studentNumber) {
		List<Post> posts = this.postDao.findByStudent_StudentNumber(studentNumber);

		if (posts.size() > 0) {
			List<GetAllPostsResponse> response = posts.stream()
					.map(post -> this.mapperService.forResponse().map(post, GetAllPostsResponse.class))
					.collect(Collectors.toList());

			return new SuccessDataResult<List<GetAllPostsResponse>>(response, Messages.postsListMessage);
		} else {
			return new ErrorDataResult<>(Messages.notFindPost);
		}

	}

	@Override
	public DataResult<List<GetAllPostsResponse>> findByStudentIdPosts(int id) {
		List<Post> posts = this.postDao.findByStudent_Id(id);
		if (posts.size() > 0) {
			return new SuccessDataResult<List<GetAllPostsResponse>>(
					posts.stream().map(post -> this.mapperService.forResponse().map(post, GetAllPostsResponse.class))
							.collect(Collectors.toList()),
					Messages.postsListMessage);
		}
		return new ErrorDataResult<>(Messages.notFindPost);
	}

	// may be requeired code refactoring
	@Override
	public DataResult<List<GetAllPostsResponse>> getAllMyFriendsPostsWithStudentDetails(String studentNumber) {
		DataResult<List<GetAllFriendshipByStudentNumber>> friendships = this.friendshipRequestService
				.getAllFriendshipByStudentNumber(studentNumber);
		List<GetAllFriendshipByStudentNumber> friends = friendships.getData();

		Sort sort = Sort.by(Direction.DESC, "loadDate");

		List<Post> posts = this.postDao.findAll(sort);
		List<Post> myFriendsPosts = new ArrayList<>();
		for (Post post : posts) {
			for (GetAllFriendshipByStudentNumber friend : friends) {
				if ((post.getStudent().getStudentNumber().equals(friend.getReceiverStudentNumber())
						|| post.getStudent().getStudentNumber().equals(friend.getSenderStudentNumber()))
						&& !post.getStudent().getStudentNumber().equals(studentNumber)) {
					myFriendsPosts.add(post);
					break;
				}
			}
		}

		List<GetAllPostsResponse> response = myFriendsPosts.stream()
				.map(post -> this.mapperService.forResponse().map(post, GetAllPostsResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllPostsResponse>>(response, Messages.postsListMessage);
	}

}
