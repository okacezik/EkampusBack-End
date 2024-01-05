package myproject.ekampus.business.abstracts;

import java.util.List;

import myproject.ekampus.business.dtos.requests.CreateStudentClubRequest;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.entities.concretes.StudentClub;

public interface StudentClubService {

	DataResult<List<StudentClub>> getAllStudentClub();

	Result add(CreateStudentClubRequest request);
}
