package myproject.ekampus.business.abstracts;

import java.util.List;

import myproject.ekampus.business.dtos.requests.CreatePostRequest;
import myproject.ekampus.business.dtos.responses.GetAllPostsResponse;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;

public interface PostService {

	DataResult<List<GetAllPostsResponse>> add(CreatePostRequest createPostRequest);

	Result delete(int postId);

	DataResult<List<GetAllPostsResponse>> getAllPostsWithStudentDetails();

	DataResult<List<GetAllPostsResponse>> getPostWithStudentDetailsSortedByLoadDate();

	DataResult<List<GetAllPostsResponse>> findByStudentNumberPosts(String studentNumber);

	DataResult<List<GetAllPostsResponse>> findByStudentIdPosts(int id);

}
