package myproject.ekampus.business.abstracts;

import java.util.List;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.entities.concretes.Post;
import myproject.ekampus.entities.dtos.PostWithStudentDto;

public interface PostService {
	
	Result add(Post post);
	
	Result delete(int postId, int ownerId);

	//DataResult<List<Post>> getAll();
	
	DataResult<List<PostWithStudentDto>> getPostWithStudentDetails();
	
	DataResult <List<PostWithStudentDto>> getPostWithStudentDetailsBySort();
	
	DataResult<List<PostWithStudentDto>> getPostWithStudentDetails(int studentId);

}
