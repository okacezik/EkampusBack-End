package myproject.ekampus.business.concretes;


import java.time.Duration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import myproject.ekampus.business.abstracts.AuthService;
import myproject.ekampus.business.dtos.requests.AuthenticateRequest;
import myproject.ekampus.business.dtos.requests.CreateStudentRequest;
import myproject.ekampus.business.dtos.responses.AuthenticatedUser;
import myproject.ekampus.core.security.JwtService;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.ErrorDataResult;
import myproject.ekampus.core.utilites.results.SuccessDataResult;
import myproject.ekampus.dataAccess.abstracts.StudentDao;
import myproject.ekampus.entities.concretes.Student;

@Service
@RequiredArgsConstructor
public class AuthManager implements AuthService {

	private final StudentDao studentDao;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	@Override
	public DataResult<String> register(CreateStudentRequest createStudentRequest) {
		Student control = this.studentDao.findByStudentNumber(createStudentRequest.getStudentNumber());
		if (control == null) {

			Student student = Student.builder().firstName(createStudentRequest.getFirstName())
					.lastName(createStudentRequest.getLastName())
					.departmantName(createStudentRequest.getDepartmantName())
					.studentNumber(createStudentRequest.getStudentNumber()).username(createStudentRequest.getUsername())
					.password(passwordEncoder.encode(createStudentRequest.getPassword())).build();

			this.studentDao.save(student);

			String token = jwtService.generateToken(student);

			return new SuccessDataResult<String>(token, "User registered succesfully");

		} else {
			return new ErrorDataResult<>("User already current");
		}

	}

	@Override
	public DataResult<AuthenticatedUser> login(AuthenticateRequest request, HttpServletResponse response) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			var user = studentDao.findByUsername(request.getUsername()).orElseThrow();
			var token = jwtService.generateToken(user);

			// JWT'yi çerez olarak ekleyerek frontend'e gönderme
			
			ResponseCookie cookie = ResponseCookie.from("accessToken", token)
		            .httpOnly(true)
		            .secure(true)
		            .path("/")
		            .maxAge(Duration.ofMillis(1000000000))
		            .build();

			 response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
			
			return new SuccessDataResult<AuthenticatedUser>(
					AuthenticatedUser.builder()
					.id(user.getId())
					.firstName(user.getFirstName())
					.lastName(user.getLastName())
					.studentNumber(user.getStudentNumber())
					.departmantName(user.getDepartmantName())
					.hiddenAccount(user.isHiddenAccount())
					.token(token)
					.build(), 
					"User login successfully");

		} catch (Exception e) {
			// e.printStackTrace();
			return new ErrorDataResult<>();
		}
	}


}
