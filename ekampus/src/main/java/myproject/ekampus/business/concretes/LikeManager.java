package myproject.ekampus.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myproject.ekampus.business.abstracts.LikeService;
import myproject.ekampus.business.abstracts.StudentService;
import myproject.ekampus.business.dtos.requests.LikeThePostRequest;
import myproject.ekampus.business.dtos.requests.RemoveLikeFromThePostRequest;
import myproject.ekampus.business.dtos.responses.GetStudentByStudentNumber;
import myproject.ekampus.business.dtos.responses.LikedPost;
import myproject.ekampus.core.utilites.mappers.ModelMapperService;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.ErrorDataResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.core.utilites.results.SuccessDataResult;
import myproject.ekampus.core.utilites.results.SuccessResult;
import myproject.ekampus.dataAccess.abstracts.LikeDao;
import myproject.ekampus.entities.concretes.Like;

@Slf4j
@AllArgsConstructor
@Service
public class LikeManager implements LikeService {

	@Autowired
	private LikeDao likeDao;
	private ModelMapperService mapperService;
	private StudentService studentService;

	@Override
	public Result likeThePost(LikeThePostRequest likeRequest) {
		Optional<Like> foundLike = this.likeDao.findByPost_IdAndLikeStudent_Id(likeRequest.getPostId(),
				likeRequest.getLikeStudentId());

		if (foundLike.isPresent()) {
			return new SuccessResult("Already liked post");
		}

		Like like = this.mapperService.forRequest().map(likeRequest, Like.class);
		this.likeDao.save(like);
		return new SuccessResult("Post liked");
	}

	@Override
	public Result removeLikeFromThePost(RemoveLikeFromThePostRequest removeLikeFromThePostRequest) {
		Optional<Like> foundLike = this.likeDao.findByPost_IdAndLikeStudent_Id(removeLikeFromThePostRequest.getPostId(),
				removeLikeFromThePostRequest.getLikeStudentId());
		if (foundLike.isPresent()) {
			this.likeDao.delete(foundLike.get());
			return new SuccessResult("Removed like");
		}
		return new ErrorDataResult<>("Not found like");

	}

	@Override
	public DataResult<List<LikedPost>> getLikedPostsByStudentNumber(String studentNumber) {
		DataResult<GetStudentByStudentNumber> response = this.studentService.getByStudentNumberStudent(studentNumber);

		if (response.isSuccess()) {
			Optional<List<Like>> likes = this.likeDao.findByLikeStudent_Id(response.getData().getId());

			if (likes.isPresent()) {

				List<LikedPost> likedPosts = likes.get().stream()
						.map(like -> this.mapperService.forResponse().map(like, LikedPost.class))
						.collect(Collectors.toList());

				return new SuccessDataResult<List<LikedPost>>(likedPosts, studentNumber);

			}
		}

		log.info("There is not student");

		return null;
	}
}
