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
import lombok.extern.slf4j.Slf4j;
import myproject.ekampus.business.abstracts.LikeService;
import myproject.ekampus.business.dtos.requests.LikeThePostRequest;
import myproject.ekampus.business.dtos.requests.RemoveLikeFromThePostRequest;
import myproject.ekampus.business.dtos.responses.LikedPost;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/like")
@CrossOrigin
@Slf4j
public class LikesController {

	private LikeService likeService;
	
	@PostMapping("/")
	public Result likeThePost(@RequestBody() LikeThePostRequest like) {
		log.info("request like");
		return this.likeService.likeThePost(like);
	}
	
	@DeleteMapping("/")
	public Result removeLike(@RequestBody() RemoveLikeFromThePostRequest removeLike) {
		log.info("request remove like");
		log.info(removeLike.getPostId()+" POST "+removeLike.getLikeStudentId()+" STUDENT");
		return this.likeService.removeLikeFromThePost(removeLike);
	}
	
	@GetMapping()
	public DataResult<List<LikedPost>> getLikedPostsByStudentNumber(@RequestParam String studentNumber){
		return this.likeService.getLikedPostsByStudentNumber(studentNumber);
	}
	
}
