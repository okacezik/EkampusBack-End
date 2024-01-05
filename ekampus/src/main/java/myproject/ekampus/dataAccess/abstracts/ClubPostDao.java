package myproject.ekampus.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import myproject.ekampus.entities.concretes.ClubPost;

public interface ClubPostDao extends JpaRepository<ClubPost, Integer>{

}
