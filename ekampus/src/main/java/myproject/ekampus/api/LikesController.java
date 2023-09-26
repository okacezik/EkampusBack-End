package myproject.ekampus.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import myproject.ekampus.business.abstracts.LikeService;
import myproject.ekampus.business.dtos.requests.LikeThePostRequest;
import myproject.ekampus.business.dtos.requests.RemovetLikeFromThePostRequest;
import myproject.ekampus.core.utilites.results.Result;

@AllArgsConstructor
@RestController
@RequestMapping("/api/like")
public class LikesController {

	private LikeService likeService;
	
	@PostMapping("/")
	public Result likeThePost(LikeThePostRequest like) {
		return this.likeService.likeThePost(like);
	}
	
	@DeleteMapping("/")
	public Result removeLike(RemovetLikeFromThePostRequest removeLike) {
		return this.likeService.removeLikeFromThePost(removeLike);
	}
	
}
