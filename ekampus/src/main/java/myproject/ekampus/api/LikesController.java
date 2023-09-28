package myproject.ekampus.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myproject.ekampus.business.abstracts.LikeService;
import myproject.ekampus.business.dtos.requests.LikeThePostRequest;
import myproject.ekampus.business.dtos.requests.RemovetLikeFromThePostRequest;
import myproject.ekampus.core.utilites.results.Result;

@AllArgsConstructor
@RestController
@RequestMapping("/api/like")
@CrossOrigin
@Slf4j
public class LikesController {

	private LikeService likeService;
	
	@PostMapping("/")
	public Result likeThePost(@RequestBody LikeThePostRequest like) {
		log.info("request like");
		return this.likeService.likeThePost(like);
	}
	
	@DeleteMapping("/")
	public Result removeLike(@RequestBody RemovetLikeFromThePostRequest removeLike) {
		log.info("request remove like");
		return this.likeService.removeLikeFromThePost(removeLike);
	}
	
}
