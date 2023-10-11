package myproject.ekampus.business.abstracts;

import java.util.List;

import myproject.ekampus.business.dtos.requests.CreatePostRequest;
import myproject.ekampus.business.dtos.responses.GetAllPostsResponse;
import myproject.ekampus.business.dtos.responses.GetLikeByPostId;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;

public interface PostService {

	DataResult<List<GetAllPostsResponse>> add(CreatePostRequest createPostRequest);

	Result delete(int postId);

	DataResult<List<GetAllPostsResponse>> getPostWithStudentDetailsSortedByLoadDate();

	DataResult<List<GetAllPostsResponse>> findByStudentNumberPosts(String studentNumber);

	DataResult<List<GetAllPostsResponse>> getAllMyFriendsPostsWithStudentDetails(String studentNumber);
	
	DataResult<List<GetLikeByPostId>> getLikes(int postId);

}
