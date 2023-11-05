package myproject.ekampus.api;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myproject.ekampus.business.abstracts.StudentImageService;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/student_images")
@CrossOrigin
@CacheConfig(cacheNames = "students_images")
public class StudentImagesController {

	private StudentImageService service;

	@PostMapping("/add")
	public byte[] uploadStudentImage(@RequestParam("id") int studentId, @RequestPart("file") MultipartFile file) {
		
		log.info(file.getContentType());
		log.info("request coming");
		return this.service.uploadStudentImage(studentId, file);
	}
	
	@GetMapping("/get")
	@Cacheable(value = "students_images", key = "#studentId")
	public byte[] getStudentImage(@RequestParam("id") int studentId){
		log.info(studentId+" 's photo getting from db");
		return this.service.getStudentImage(studentId);
	}
}
