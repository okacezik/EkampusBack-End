package myproject.ekampus.dataAccess.abstracts;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import myproject.ekampus.entities.concretes.Student;
import myproject.ekampus.entities.dtos.StudentDetailDto;

public interface StudentDao extends JpaRepository<Student, Integer>{
			
	//List<Student> findByFirstNameContains(String studentName);
	
	//List<Student> getByFirstNameStartsWith(String studentName);
	
	@Query("Select new myproject.ekampus.entities.dtos.StudentDetailDto"
			+ "(s.id,s.studentNumber,s.departmantName,s.firstName,s.lastName,s.password,s.studentPhotoPath) "
			+ "From Student s")
	List<StudentDetailDto> getAllStudent();
}
