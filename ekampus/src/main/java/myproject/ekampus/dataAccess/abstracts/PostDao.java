package myproject.ekampus.dataAccess.abstracts;


import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import myproject.ekampus.entities.concretes.Post;

public interface PostDao extends JpaRepository<Post, Integer>{
	
	/*
	@Query("Select new myproject.ekampus.entities.dtos."
			+ "PostWithStudentDto"
			+ "(p.id,s.id,p.comment,p.postPhotoPath,p.loadDate,s.firstName,s.lastName"
			+ ",s.studentPhotoPath) "
			+ "From Student s Inner Join s.posts p")
	List<PostWithStudentDto> getPostWithStudentDetails();
	
	*/
	
	List<Post> findByStudent_StudentNumber(String studentNumber, Sort sort);
	
	List<Post> findByStudent_Id(int id);
}
