package myproject.ekampus.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import myproject.ekampus.entities.concretes.StudentImage;

public interface StudentImageDao extends JpaRepository<StudentImage, Integer> {
	StudentImage findByStudentId(int id);

}
