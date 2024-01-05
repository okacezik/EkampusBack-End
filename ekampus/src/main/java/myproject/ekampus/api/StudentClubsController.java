package myproject.ekampus.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import myproject.ekampus.business.abstracts.StudentClubService;
import myproject.ekampus.business.dtos.requests.CreateStudentClubRequest;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.entities.concretes.StudentClub;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/student-clubs")
@RequiredArgsConstructor
public class StudentClubsController {

	private final StudentClubService studentClubService;
	
	@GetMapping
	public DataResult<List<StudentClub>> getAllStudentClub(){
		return this.studentClubService.getAllStudentClub();
	}
	
	@PostMapping
	public Result add(@RequestBody CreateStudentClubRequest request) {
		return this.studentClubService.add(request);
	}

}
