package myproject.ekampus.business.abstracts;

import java.util.List;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.entities.concretes.Student;
import myproject.ekampus.entities.dtos.StudentDetailDto;

public interface StudentService {
	
	Result add(Student student);
	
	Result delete(String password, String studentNumber);
	
	Result entryStudent(String password, String studentNumber);
	
	//DataResult<List<Student>> getAll();
	
	DataResult<StudentDetailDto> findByStudentId(int id);
	
	DataResult<StudentDetailDto> findByStudentName(String studentName);
	
	DataResult<List<StudentDetailDto>> findByStudentNameContains(String studentName);
	
	DataResult<List<StudentDetailDto>> getByFirstNameStartsWith(String studentName);
	
	DataResult<List<StudentDetailDto>> getAllStudent();
	
}
