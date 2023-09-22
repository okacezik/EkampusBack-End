package myproject.ekampus.business.abstracts;

import org.springframework.web.multipart.MultipartFile;

import myproject.ekampus.core.utilites.results.Result;

public interface StudentImageService {

	Result uploadStudentImage(int studentId, MultipartFile file);
	
	byte[] getStudentImage(int studentId);
}
