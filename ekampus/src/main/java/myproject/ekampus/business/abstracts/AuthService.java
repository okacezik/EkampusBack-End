package myproject.ekampus.business.abstracts;

import myproject.ekampus.business.dtos.requests.AuthenticateRequest;
import myproject.ekampus.business.dtos.requests.CreateStudentRequest;
import myproject.ekampus.core.utilites.results.DataResult;

public interface AuthService {

	DataResult<String> register(CreateStudentRequest createStudentRequest);

	DataResult<String> login(AuthenticateRequest request);
}
