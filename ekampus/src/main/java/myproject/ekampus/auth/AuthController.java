package myproject.ekampus.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import myproject.ekampus.business.abstracts.AuthService;
import myproject.ekampus.business.dtos.requests.AuthenticateRequest;
import myproject.ekampus.business.dtos.requests.CreateStudentRequest;
import myproject.ekampus.business.dtos.responses.AuthenticatedUser;
import myproject.ekampus.core.utilites.results.DataResult;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/register")
	public DataResult<String> register(@RequestBody CreateStudentRequest request) {
		return authService.register(request);
	}
	
	@PostMapping("/login")
	public DataResult<AuthenticatedUser> login(@RequestBody AuthenticateRequest request) {
		return authService.login(request);
	}
	
}
