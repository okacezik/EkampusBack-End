package myproject.ekampus.business.concretes;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import myproject.ekampus.business.BusinessRules.PostBusinessRules;
import myproject.ekampus.business.abstracts.PostService;
import myproject.ekampus.business.dtos.requests.CreatePostRequest;
import myproject.ekampus.core.utilites.mappers.ModelMapperService;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.ErrorResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.core.utilites.results.SuccessDataResult;
import myproject.ekampus.core.utilites.results.SuccessResult;
import myproject.ekampus.dataAccess.abstracts.PostDao;
import myproject.ekampus.entities.concretes.Post;
import myproject.ekampus.entities.dtos.PostWithStudentDto;

@Service
@AllArgsConstructor
public class PostManager implements PostService {

	private PostDao postDao;
	private List<Post> posts;
	private ModelMapperService mapperService;

	@Override
	public Result add(CreatePostRequest createPostRequest) {
		Post post = this.mapperService.forRequest().map(createPostRequest, Post.class);
		this.postDao.save(post);
		return new SuccessResult(Messages.postAddMessage);
	}

	@Override
	public Result delete(int postId, int ownerId) {

		Post post = PostBusinessRules.existPostControl(posts, postId);

		if (post == null) {
			return new ErrorResult(Messages.notFindPost);
		} else {
			this.postDao.delete(post);
			return new SuccessResult(Messages.postDeleteMessage);
		}
	}

	@Override
	public DataResult<List<PostWithStudentDto>> getPostWithStudentDetails() {
		return new SuccessDataResult<List<PostWithStudentDto>>(this.postDao.getPostWithStudentDetails(),
				Messages.postsListMessage);
	}

	@Override
	public DataResult<List<PostWithStudentDto>> getPostWithStudentDetails(int studentId) {
		List<PostWithStudentDto> studentPosts = new ArrayList<PostWithStudentDto>();
		for (PostWithStudentDto post : this.postDao.getPostWithStudentDetails()) {
			if (post.getStudentId() == studentId) {
				studentPosts.add(post);
			}
		}
		return new SuccessDataResult<List<PostWithStudentDto>>(studentPosts, Messages.postsListMessage);
	}

	@Override
	public DataResult<List<PostWithStudentDto>> getPostWithStudentDetailsSortedByLoadDate() {
		// Sort sort = Sort.by(Sort.Direction.DESC,"loadDate");
		List<PostWithStudentDto> sortedPosts = this.postDao.getPostWithStudentDetails();
		// Collections.reverse(sortedPosts);

		return new SuccessDataResult<List<PostWithStudentDto>>(PostBusinessRules.getAllPostByReverse(sortedPosts),
				Messages.postsListMessage);
	}
}
