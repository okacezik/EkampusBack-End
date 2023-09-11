package myproject.ekampus.api;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import myproject.ekampus.business.abstracts.PostService;
import myproject.ekampus.business.dtos.requests.CreatePostRequest;
import myproject.ekampus.business.dtos.responses.GetAllPostsResponse;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin
@AllArgsConstructor
public class PostsController {
	
	private PostService postService;
	
	
	@PostMapping("/add")
	public Result add(@RequestBody CreatePostRequest createPostRequest) {
		return this.postService.add(createPostRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestParam int id) {
		return this.postService.delete(id);
	}
	
	@GetMapping("/getPostWithStudentDetails")
	public DataResult<List<GetAllPostsResponse>> getPostWithStudentDetails(){
		return this.postService.getAllPostsWithStudentDetails();
	}
	
	@GetMapping("/getPostsByStudentId")
	public DataResult<List<GetAllPostsResponse>> getPostsByStudentId(@RequestParam int studentId){
		return this.postService.findByStudentIdPosts(studentId);
	}
	
	@GetMapping("/getPostDetailsBySort")
	public DataResult<List<GetAllPostsResponse>> getPostWithStudentDetailsSortedByLoadDate(){
		return this.postService.getPostWithStudentDetailsSortedByLoadDate();
	}
	
	@GetMapping("/getPostsByStudentNumber")
	public DataResult<List<GetAllPostsResponse>> findByStudent_StudentNumber(String studentNumber){
		return this.postService.findByStudentNumberPosts(studentNumber);
	}
}
