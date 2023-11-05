package myproject.ekampus.dataAccess.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import myproject.ekampus.entities.concretes.Student;

public interface StudentDao extends JpaRepository<Student, Integer> {

	/*
	 * @Query("Select new myproject.ekampus.entities.dtos.StudentDetailDto" +
	 * "(s.id,s.studentNumber,s.departmantName,s.firstName,s.lastName,s.password,s.studentPhotoPath) "
	 * + "From Student s") List<StudentDetailDto> getAllStudent();
	 */

	List<Student> findByFirstNameContains(String name);

	List<Student> findByFirstNameStartsWith(String name);

	Student findByFirstName(String name);
	
	Student findByStudentNumberAndPassword(String studentNumber, String password);
	
	Student findByStudentNumber(String studentNumber);
	
	Optional<Student> findByUsername(String username);

}
