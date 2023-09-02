package myproject.ekampus.business.abstracts;

import java.util.List;

import myproject.ekampus.business.dtos.requests.CreateStudentRequest;
import myproject.ekampus.business.dtos.responses.GetAllStudentsResponse;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.entities.dtos.StudentDetailDto;

public interface StudentService {
	
	Result add(CreateStudentRequest createStudentRequest);
	
	Result delete(String password, String studentNumber);
	
	DataResult<Boolean> entryStudent(String password, String studentNumber);
	
	//DataResult<List<Student>> getAll();
	
	DataResult<StudentDetailDto> findByStudentId(int id);
	
	DataResult<StudentDetailDto> findByStudentName(String studentName);
	
	DataResult<List<StudentDetailDto>> findByStudentNameContains(String studentName);
	
	DataResult<List<StudentDetailDto>> getByFirstNameStartsWith(String studentName);
	
	//DataResult<List<StudentDetailDto>> getAllStudent();
	
	DataResult<List<GetAllStudentsResponse>> getAllStudents();
	
	DataResult<List<StudentDetailDto>> getAllStudentBySorted();
	
}
