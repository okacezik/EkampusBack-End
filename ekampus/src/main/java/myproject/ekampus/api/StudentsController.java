package myproject.ekampus.api;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import myproject.ekampus.business.abstracts.StudentService;
import myproject.ekampus.business.dtos.requests.CreateStudentRequest;
import myproject.ekampus.business.dtos.requests.LogInStudent;
import myproject.ekampus.business.dtos.responses.GetAllStudentsResponse;
import myproject.ekampus.business.dtos.responses.GetByIdStudentResponse;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
@AllArgsConstructor
public class StudentsController {
	
	private StudentService studentService;
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateStudentRequest createStudentRequest) {
		return this.studentService.add(createStudentRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestParam("password") String password,@RequestParam("studentNumber") String studentNumber) {
		return this.studentService.delete(password, studentNumber);
	}
	
	@GetMapping("getByStudentFirstName")
	public DataResult<GetAllStudentsResponse> getByFirstName(@RequestParam String firstName){
		return this.studentService.findByStudentName(firstName);
	}
	
	@GetMapping("/getByFirstNameContains")
	public DataResult<List<GetAllStudentsResponse>> getByStudentNameContains(@RequestParam String studentName){
		return this.studentService.findByStudentNameContains(studentName);
	}
	

	@GetMapping("/getByFirstNameStartsWith")
	public DataResult<List<GetAllStudentsResponse>> getByFirstNameStartsWith(@RequestParam String studentName){
		return this.studentService.findByFirstNameStartsWith(studentName);
	}
	
	@GetMapping("/getAllStudents")
	public DataResult<List<GetAllStudentsResponse>> getAllStudent(){
		return this.studentService.getAllStudents();
	}
	
	@GetMapping("/getByIdStudent")
	public DataResult<GetByIdStudentResponse> getStudentByStudentId(@RequestParam int id){
		return this.studentService.getByIdStudent(id);
	}
	
	@GetMapping("/getAllStudentBySorted")
	public DataResult<List<GetAllStudentsResponse>> getAllStudentBySorted(){
		return this.studentService.getAllStudentBySorted();
	}
	
	@PostMapping("/entry")
	public DataResult<GetByIdStudentResponse> entryStudent2(@RequestBody LogInStudent login) {
		 return this.studentService.findByStudentNumberAndPassword(login);
	}
	
	@GetMapping("/getByStudentNumberStudent")
	public DataResult<GetAllStudentsResponse> getByStudentNumberStudent(@RequestParam String studentNumber){
		return this.studentService.getByStudentNumberStudent(studentNumber);
	}
}
