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
import myproject.ekampus.business.dtos.responses.GetLikeByPostId;
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
	@CachePut(value = "posts", key = "1") // key provides to store data unique
	public DataResult<List<GetAllPostsResponse>> add(@RequestBody CreatePostRequest createPostRequest) {
		return this.postService.add(createPostRequest);
	}

	@DeleteMapping("/delete")
	@CacheEvict(value = "posts", key = "1", condition = "#result.success != false")
	public Result delete(@RequestParam int id) {
		return this.postService.delete(id);
	}

	@GetMapping("/getPostWithStudentDetails")
	public DataResult<List<GetAllPostsResponse>> getPostWithStudentDetails() {
		return this.postService.getAllPostsWithStudentDetails();
	}

	@GetMapping("/getPostsByStudentId")
	public DataResult<List<GetAllPostsResponse>> getPostsByStudentId(@RequestParam int studentId) {
		return this.postService.findByStudentIdPosts(studentId);
	}

	@GetMapping("/getPostDetailsBySort")
	@Cacheable(value = "posts", key = "1", unless = "#result == null")
	public DataResult<List<GetAllPostsResponse>> getPostWithStudentDetailsSortedByLoadDate() {
		log.info("Getting posts from DB");
		return this.postService.getPostWithStudentDetailsSortedByLoadDate();
	}

	@GetMapping("/getPostsByStudentNumber")
	public DataResult<List<GetAllPostsResponse>> findByStudent_StudentNumber(@RequestParam String studentNumber) {
		return this.postService.findByStudentNumberPosts(studentNumber);
	}

	@GetMapping("/getAllMyFriendsPosts")
	@Cacheable(value = "posts", key = "2", unless = "#result == null")
	public DataResult<List<GetAllPostsResponse>> getAllMyFriendsPostsWithStudentDetails(
			@RequestParam String studentNumber) {
		log.info("Getting my friends posts from DB");
		return this.postService.getAllMyFriendsPostsWithStudentDetails(studentNumber);
	}

	@GetMapping("/likes")
	public DataResult<List<GetLikeByPostId>> getLikes(@RequestParam int postId) {
		return this.postService.getLikes(postId);
	}
}
