package myproject.ekampus.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import myproject.ekampus.business.abstracts.PostService;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.entities.concretes.Post;
import myproject.ekampus.entities.dtos.PostWithStudentDto;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin
public class PostsController {
	
	private PostService postService;
	
	@Autowired
	public PostsController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Post post) {
		return this.postService.add(post);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestParam("id") int id,@RequestParam("ownerId") int ownerId) {
		return this.postService.delete(id, ownerId);
	}
	
	@GetMapping("/getPostDetails")
	public DataResult<List<PostWithStudentDto>> getPostWithStudentDetails(){
		return this.postService.getPostWithStudentDetails();
	}
}
