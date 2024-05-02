package myproject.ekampus.business.abstracts;

import jakarta.servlet.http.HttpServletResponse;
import myproject.ekampus.business.dtos.requests.AuthenticateRequest;
import myproject.ekampus.business.dtos.requests.CreateStudentRequest;
import myproject.ekampus.business.dtos.responses.AuthenticatedUser;
import myproject.ekampus.core.utilites.results.DataResult;

public interface AuthService {

	DataResult<String> register(CreateStudentRequest createStudentRequest);

	DataResult<AuthenticatedUser> login(AuthenticateRequest request, HttpServletResponse response);
}
