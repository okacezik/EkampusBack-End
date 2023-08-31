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
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.entities.dtos.PostWithStudentDto;

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
	public Result delete(@RequestParam("id") int id,@RequestParam("ownerId") int ownerId) {
		return this.postService.delete(id, ownerId);
	}
	
	@GetMapping("/getPostDetails")
	public DataResult<List<PostWithStudentDto>> getPostWithStudentDetails(){
		return this.postService.getPostWithStudentDetails();
	}
	
	@GetMapping("/getPostsByStudentId")
	public DataResult<List<PostWithStudentDto>> getPostsByStudentId(@RequestParam int studentId){
		return this.postService.getPostWithStudentDetails(studentId);
	}
	
	@GetMapping("/getPostDetailsBySort")
	public DataResult<List<PostWithStudentDto>> getPostWithStudentDetailsSortedByLoadDate(){
		return this.postService.getPostWithStudentDetailsSortedByLoadDate();
	}
}
