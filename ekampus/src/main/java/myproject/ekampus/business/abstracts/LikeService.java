package myproject.ekampus.business.abstracts;

import myproject.ekampus.business.dtos.requests.LikeThePostRequest;
import myproject.ekampus.business.dtos.requests.RemovetLikeFromThePostRequest;
import myproject.ekampus.core.utilites.results.Result;

public interface LikeService {

	Result likeThePost(LikeThePostRequest likeRequest);

	Result removeLikeFromThePost(RemovetLikeFromThePostRequest removetLikeFromThePostRequest);
}
