package myproject.ekampus.dataAccess.abstracts;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import myproject.ekampus.entities.concretes.StudentClub;

public interface StudentClubDao extends JpaRepository<StudentClub, Integer>{

	Optional<StudentClub> findByUsername(String username);
}
