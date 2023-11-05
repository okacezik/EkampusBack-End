package myproject.ekampus.business.concretes;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import myproject.ekampus.business.abstracts.StudentImageService;
import myproject.ekampus.business.dtos.requests.UploadStudentImageRequest;
import myproject.ekampus.core.utilites.mappers.ModelMapperService;
import myproject.ekampus.dataAccess.abstracts.StudentImageDao;
import myproject.ekampus.entities.concretes.StudentImage;

@AllArgsConstructor
@Service
public class StudentImageManager implements StudentImageService {
	private StudentImageDao imageDao;
	private ModelMapperService mapperService;

	@Override
	public byte[] uploadStudentImage(int studentId, MultipartFile file) {
		Optional<StudentImage> image = this.imageDao.findByStudentId(studentId);

		if (!image.isPresent()) {
			UploadStudentImageRequest uploadStudentImageRequest = new UploadStudentImageRequest();
			uploadStudentImageRequest.setStudentId(studentId);
			try {
				uploadStudentImageRequest.setStudentPhoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			StudentImage uploadImage = this.mapperService.forRequest().map(uploadStudentImageRequest,
					StudentImage.class);
			this.imageDao.save(uploadImage);
			return uploadImage.getStudentPhoto();
		}

		this.imageDao.deleteById(image.get().getId());
		UploadStudentImageRequest uploadStudentImageRequest = new UploadStudentImageRequest();
		uploadStudentImageRequest.setStudentId(studentId);
		try {
			uploadStudentImageRequest.setStudentPhoto(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		StudentImage uploadImage = this.mapperService.forRequest().map(uploadStudentImageRequest, StudentImage.class);
		this.imageDao.save(uploadImage);
		return uploadImage.getStudentPhoto();
	}

	@Override
	public byte[] getStudentImage(int studentId) {
		Optional<StudentImage> image = this.imageDao.findByStudentId(studentId);

		return image.isPresent() ? image.get().getStudentPhoto() : null;
	}

}
