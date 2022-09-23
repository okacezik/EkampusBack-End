package myproject.ekampus.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import myproject.ekampus.business.abstracts.StudentService;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.entities.concretes.Student;
import myproject.ekampus.entities.dtos.StudentDetailDto;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentsController {
	
	private StudentService studentService;
	
	@Autowired
	public StudentsController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Student student) {
		return this.studentService.add(student);
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
	public Result entryStudent(@RequestParam("password") String password,@RequestParam("studentNumber") String studentNumber) {
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
	
	@GetMapping("/getAllStudents")
	public DataResult<List<StudentDetailDto>> getAllStudent(){
		return this.studentService.getAllStudent();
	}
	
	@GetMapping("/getStudentByStudentId")
	public DataResult<StudentDetailDto> getStudentByStudentId(@RequestParam int id){
		return this.studentService.findByStudentId(id);
	}
}
