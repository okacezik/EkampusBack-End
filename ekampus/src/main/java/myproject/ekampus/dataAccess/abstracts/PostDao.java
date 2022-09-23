package myproject.ekampus.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import myproject.ekampus.entities.concretes.Post;
import myproject.ekampus.entities.dtos.PostWithStudentDto;

public interface PostDao extends JpaRepository<Post, Integer>{
	
	@Query("Select new myproject.ekampus.entities.dtos."
			+ "PostWithStudentDto"
			+ "(p.id,s.studentId,p.comment,p.postPhotoPath,p.loadDate,s.firstName,s.lastName"
			+ ",s.studentPhotoPath) "
			+ "From Student s Inner Join s.posts p")
	List<PostWithStudentDto> getPostWithStudentDetails();
}
