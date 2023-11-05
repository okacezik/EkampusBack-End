package myproject.ekampus.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import myproject.ekampus.entities.concretes.StudentImage;

public interface StudentImageDao extends JpaRepository<StudentImage, Integer> {
	Optional<StudentImage> findByStudentId(int id);

}
