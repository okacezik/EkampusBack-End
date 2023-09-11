package myproject.ekampus.business.abstracts;

import java.util.List;

import myproject.ekampus.business.dtos.requests.CreateStudentRequest;
import myproject.ekampus.business.dtos.requests.DeleteStudentRequest;
import myproject.ekampus.business.dtos.requests.HiddenAccountRequest;
import myproject.ekampus.business.dtos.requests.LogInStudent;
import myproject.ekampus.business.dtos.responses.GetAllStudentsResponse;
import myproject.ekampus.business.dtos.responses.GetByIdStudentResponse;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;

public interface StudentService {

	Result add(CreateStudentRequest createStudentRequest);

	Result delete(DeleteStudentRequest deleteStudentRequest);

	DataResult<GetByIdStudentResponse> getByIdStudent(int id);

	DataResult<GetAllStudentsResponse> getByStudentNumberStudent(String studentNumber);

	DataResult<GetAllStudentsResponse> findByStudentFirstName(String studentName);

	DataResult<List<GetAllStudentsResponse>> findByStudentNameContains(String studentName);

	DataResult<List<GetAllStudentsResponse>> findByFirstNameStartsWith(String studentName);

	DataResult<List<GetAllStudentsResponse>> getAllStudents();

	DataResult<List<GetAllStudentsResponse>> getAllStudentBySorted();

	DataResult<GetByIdStudentResponse> findByStudentNumberAndPassword(LogInStudent login);

	Result hiddenAccountRequest(HiddenAccountRequest hiddenAccountRequest);
	
	DataResult<Boolean> isHiddenAccountByStudentNumber(String studentNumber);
}
