package myproject.ekampus.business.abstracts;

import myproject.ekampus.business.dtos.requests.AuthenticateRequest;
import myproject.ekampus.business.dtos.requests.CreateStudentRequest;
import myproject.ekampus.business.dtos.responses.AuthResponse;
import myproject.ekampus.core.utilites.results.DataResult;

public interface AuthService {

	DataResult<AuthResponse> register(CreateStudentRequest createStudentRequest);

	DataResult<AuthResponse> login(AuthenticateRequest request);
}
