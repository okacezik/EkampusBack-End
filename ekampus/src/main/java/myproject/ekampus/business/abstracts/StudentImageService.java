package myproject.ekampus.business.abstracts;

import org.springframework.web.multipart.MultipartFile;


public interface StudentImageService {

	byte[] uploadStudentImage(int studentId, MultipartFile file);
	
	byte[] getStudentImage(int studentId);
	
	//Result updateStudentImage(int studentId, )
	
}
