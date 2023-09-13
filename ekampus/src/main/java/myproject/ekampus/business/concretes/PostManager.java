package myproject.ekampus.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import myproject.ekampus.business.abstracts.PostService;
import myproject.ekampus.business.dtos.requests.CreatePostRequest;
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

	@Override
	public DataResult<List<GetAllPostsResponse>> getAllPostsWithStudentDetails() {
		List<Post> posts = this.postDao.findAll();
		List<GetAllPostsResponse> response = posts.stream()
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
}
