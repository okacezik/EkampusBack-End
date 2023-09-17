package myproject.ekampus.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import myproject.ekampus.business.abstracts.LikeService;
import myproject.ekampus.business.dtos.requests.LikeThePostRequest;
import myproject.ekampus.business.dtos.requests.RemovetLikeFromThePostRequest;
import myproject.ekampus.core.utilites.mappers.ModelMapperService;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.core.utilites.results.SuccessResult;
import myproject.ekampus.dataAccess.abstracts.LikeDao;
import myproject.ekampus.entities.concretes.Like;

@AllArgsConstructor
@Service
public class LikeManager implements LikeService {

	@Autowired
	private LikeDao likeDao;
	private ModelMapperService mapperService;

	@Override
	public Result likeThePost(LikeThePostRequest likeRequest) {
		Like like = this.mapperService.forRequest().map(likeRequest, Like.class);
		this.likeDao.save(like);
		return new SuccessResult("Post liked");
	}

	@Override
	public Result removeLikeFromThePost(RemovetLikeFromThePostRequest removetLikeFromThePostRequest) {
		Like like = this.mapperService.forRequest().map(removetLikeFromThePostRequest, Like.class);
		this.likeDao.delete(like);
		return new SuccessResult("Removed like");
	}

}
