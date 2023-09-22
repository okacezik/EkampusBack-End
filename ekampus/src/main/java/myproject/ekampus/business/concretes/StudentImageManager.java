package myproject.ekampus.business.concretes;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import myproject.ekampus.business.abstracts.StudentImageService;
import myproject.ekampus.business.dtos.requests.UploadStudentImageRequest;
import myproject.ekampus.core.utilites.mappers.ModelMapperService;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.core.utilites.results.SuccessResult;
import myproject.ekampus.dataAccess.abstracts.StudentImageDao;
import myproject.ekampus.entities.concretes.StudentImage;

@AllArgsConstructor
@Service
public class StudentImageManager implements StudentImageService {
	private StudentImageDao imageDao;
	private ModelMapperService mapperService;

	@Override
	public Result uploadStudentImage(int studentId, MultipartFile file) {

		UploadStudentImageRequest uploadStudentImageRequest = new UploadStudentImageRequest();
		uploadStudentImageRequest.setStudentId(studentId);
		try {
			uploadStudentImageRequest.setStudentPhoto(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		StudentImage image = this.mapperService.forRequest().map(uploadStudentImageRequest, StudentImage.class);
		this.imageDao.save(image);
		return new SuccessResult("Uploaded photo");
	}

	@Override
	public byte[] getStudentImage(int studentId) {
		StudentImage image = this.imageDao.findByStudentId(studentId);
		return image!=null ?
		 image.getStudentPhoto() : null ;
			
	}

}
