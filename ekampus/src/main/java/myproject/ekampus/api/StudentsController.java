package myproject.ekampus.api;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myproject.ekampus.business.abstracts.StudentService;
import myproject.ekampus.business.dtos.requests.CreateStudentRequest;
import myproject.ekampus.business.dtos.requests.DeleteStudentRequest;
import myproject.ekampus.business.dtos.requests.HiddenAccountRequest;
import myproject.ekampus.business.dtos.requests.LogInStudent;
import myproject.ekampus.business.dtos.responses.GetAllStudentsResponse;
import myproject.ekampus.business.dtos.responses.GetByIdStudentResponse;
import myproject.ekampus.business.dtos.responses.GetStudentByStudentNumber;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;

@Slf4j
@CrossOrigin
@AllArgsConstructor
@CacheConfig(cacheNames = "students")
@RestController
@RequestMapping("/api/students")
public class StudentsController {

	private StudentService studentService;
	

	@PostMapping("/add")
	@CachePut(value = "students", key = "1")
	public Result add(@RequestBody CreateStudentRequest createStudentRequest) {
		return this.studentService.add(createStudentRequest);
	}

	@DeleteMapping("/delete")
	@CacheEvict(value = "students", key = "1", condition = "#result.success != false")
	public Result delete(@RequestBody DeleteStudentRequest deleteStudentRequest) {
		return this.studentService.delete(deleteStudentRequest);
	}

	@GetMapping("getByStudentFirstName")
	public DataResult<GetAllStudentsResponse> findByStudentFirstName(@RequestParam String firstName) {
		return this.studentService.findByStudentFirstName(firstName);
	}

	@GetMapping("/getByFirstNameContains")
	public DataResult<List<GetAllStudentsResponse>> getByStudentNameContains(@RequestParam String studentName) {
		return this.studentService.findByStudentNameContains(studentName);
	}

	@GetMapping("/getByFirstNameStartsWith")
	public DataResult<List<GetAllStudentsResponse>> getByFirstNameStartsWith(@RequestParam String studentName) {
		return this.studentService.findByFirstNameStartsWith(studentName);
	}

	@GetMapping("/getByIdStudent")
	public DataResult<GetByIdStudentResponse> getByStudentId(@RequestParam int id) {
		return this.studentService.getByIdStudent(id);
	}

	@GetMapping("/getAllStudentBySorted")
	@Cacheable(value = "students", unless = "#result == null", key = "1")
	public DataResult<List<GetAllStudentsResponse>> getAllStudentsBySorted() {
		log.info("USERS REQUE");
		log.info("Getting users from db");
		return this.studentService.getAllStudentsBySorted();
	}

	@PostMapping("/entry")
	public DataResult<GetByIdStudentResponse> entryStudent(@RequestBody LogInStudent login) {
		return this.studentService.findByStudentNumberAndPassword(login);
	}

	@GetMapping("/getByStudentNumberStudent")
	public DataResult<GetStudentByStudentNumber> getByStudentNumberStudent(@RequestParam String studentNumber) {
		log.info("Getting student's info");
		return this.studentService.getByStudentNumberStudent(studentNumber);
	}

	@PutMapping("/hiddenAccountRequest")
	public Result hiddenAccountRequest(@RequestBody HiddenAccountRequest hiddenAccountRequest) {
		return this.studentService.hiddenAccountRequest(hiddenAccountRequest);
	}

	@GetMapping("/isHiddenAccount")
	public DataResult<Boolean> isHiddenAccountByStudentNumber(@RequestParam String studentNumber) {
		return this.studentService.isHiddenAccountByStudentNumber(studentNumber);
	}
}
