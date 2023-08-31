package myproject.ekampus.business.abstracts;

import java.util.List;

import myproject.ekampus.business.dtos.requests.CreatePostRequest;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.entities.dtos.PostWithStudentDto;

public interface PostService {
	
	Result add(CreatePostRequest createPostRequest);
	
	Result delete(int postId, int ownerId);

	//DataResult<List<Post>> getAll();
	
	DataResult<List<PostWithStudentDto>> getPostWithStudentDetails();
	
	DataResult <List<PostWithStudentDto>> getPostWithStudentDetailsSortedByLoadDate();
	
	DataResult<List<PostWithStudentDto>> getPostWithStudentDetails(int studentId);

}
