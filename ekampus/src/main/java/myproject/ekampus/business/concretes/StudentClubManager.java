package myproject.ekampus.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import myproject.ekampus.business.abstracts.StudentClubService;
import myproject.ekampus.business.dtos.requests.CreateStudentClubRequest;
import myproject.ekampus.core.utilites.mappers.ModelMapperService;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.ErrorResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.core.utilites.results.SuccessDataResult;
import myproject.ekampus.core.utilites.results.SuccessResult;
import myproject.ekampus.dataAccess.abstracts.StudentClubDao;
import myproject.ekampus.entities.concretes.StudentClub;

@Service
@RequiredArgsConstructor
public class StudentClubManager implements StudentClubService{
	
	private final StudentClubDao studentClubDao;
	private final ModelMapperService mapperService;

	@Override
	public DataResult<List<StudentClub>> getAllStudentClub() {
		return new SuccessDataResult<List<StudentClub>>(this.studentClubDao.findAll(), "Listed student clubs");
	}

	@Override
	public Result add(CreateStudentClubRequest request) {
		Optional<StudentClub> studentClub =  this.studentClubDao.findByUsername(request.getUsername());
		if(!studentClub.isPresent()) {
			StudentClub club = this.mapperService.forRequest().map(request, StudentClub.class);
			this.studentClubDao.save(club);
			return new SuccessResult("Student club registered");
		}
		return new ErrorResult("This club already registered");
		
	}

}
