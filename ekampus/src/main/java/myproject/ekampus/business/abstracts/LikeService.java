package myproject.ekampus.business.abstracts;

import java.util.List;

import myproject.ekampus.business.dtos.requests.LikeThePostRequest;
import myproject.ekampus.business.dtos.requests.RemoveLikeFromThePostRequest;
import myproject.ekampus.business.dtos.responses.LikedPost;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;

public interface LikeService {

	Result likeThePost(LikeThePostRequest likeRequest);

	Result removeLikeFromThePost(RemoveLikeFromThePostRequest removetLikeFromThePostRequest);
	
	DataResult<List<LikedPost>> getLikedPostsByStudentNumber(String studentNumber);
	
}
