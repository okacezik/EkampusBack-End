package myproject.ekampus.api;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myproject.ekampus.business.abstracts.PostService;
import myproject.ekampus.business.dtos.requests.CreatePostRequest;
import myproject.ekampus.business.dtos.responses.GetAllPostsResponse;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;

@Slf4j
@CacheConfig(cacheNames = "posts")
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostsController {

	private PostService postService;

	@PostMapping("/add")
	@CachePut(value = "posts", key = "1")   //key provides to store data unique
	public DataResult<List<GetAllPostsResponse>> add(@RequestBody CreatePostRequest createPostRequest) {
		return this.postService.add(createPostRequest);
	}

	@DeleteMapping("/delete")
	@CacheEvict(value = "posts", key = "1")
	public Result delete(@RequestParam int id) {
		return this.postService.delete(id);
	}

	@GetMapping("/getPostWithStudentDetails")
	public DataResult<List<GetAllPostsResponse>> getPostWithStudentDetails() {
		log.info("Getting posts 2 from DB");
		return this.postService.getAllPostsWithStudentDetails();
	}

	@GetMapping("/getPostsByStudentId")
	public DataResult<List<GetAllPostsResponse>> getPostsByStudentId(@RequestParam int studentId) {
		return this.postService.findByStudentIdPosts(studentId);
	}

	@GetMapping("/getPostDetailsBySort")
	@Cacheable(value = "posts", unless = "#result == null", key = "1")
	public DataResult<List<GetAllPostsResponse>> getPostWithStudentDetailsSortedByLoadDate() {
		log.info("Getting posts from DB");
		return this.postService.getPostWithStudentDetailsSortedByLoadDate();
	}

	@GetMapping("/getPostsByStudentNumber")
	public DataResult<List<GetAllPostsResponse>> findByStudent_StudentNumber(String studentNumber) {
		return this.postService.findByStudentNumberPosts(studentNumber);
	}
}
