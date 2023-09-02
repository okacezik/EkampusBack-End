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
import myproject.ekampus.business.dtos.responses.GetAllStudentsResponse;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.entities.dtos.StudentDetailDto;

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
	
	/*
	@GetMapping("/getAll")
	public DataResult<List<Student>> getAll(){
		return this.studentService.getAll();
	}
	*/
	
	@PostMapping("/entry")
	public DataResult<Boolean> entryStudent(@RequestParam("password") String password,@RequestParam("studentNumber") String studentNumber) {
		return this.studentService.entryStudent(password, studentNumber);
	}
	
	@GetMapping("getByStudentFirstName")
	public DataResult<StudentDetailDto> getByFirstName(@RequestParam String firstName){
		return this.studentService.findByStudentName(firstName);
	}
	
	@GetMapping("/getByFirstNameContains")
	public DataResult<List<StudentDetailDto>> getByStudentNameContains(@RequestParam String studentName){
		return this.studentService.findByStudentNameContains(studentName);
	}
	

	@GetMapping("/getByFirstNameStartsWith")
	public DataResult<List<StudentDetailDto>> getByFirstNameStartsWith(@RequestParam String studentName){
		return this.studentService.getByFirstNameStartsWith(studentName);
	}
	/*
	@GetMapping("/getAllStudents")
	public DataResult<List<StudentDetailDto>> getAllStudent(){
		return this.studentService.getAllStudent();
	}
	*/
	
	@GetMapping("/getAllStudents")
	public DataResult<List<GetAllStudentsResponse>> getAllStudent(){
		return this.studentService.getAllStudents();
	}
	
	@GetMapping("/getStudentByStudentId")
	public DataResult<StudentDetailDto> getStudentByStudentId(@RequestParam int id){
		return this.studentService.findByStudentId(id);
	}
	
	@GetMapping("/getAllStudentBySorted")
	public DataResult<List<StudentDetailDto>> getAllStudentBySorted(){
		return this.studentService.getAllStudentBySorted();
	}
}
