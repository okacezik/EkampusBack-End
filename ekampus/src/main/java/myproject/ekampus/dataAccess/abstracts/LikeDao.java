package myproject.ekampus.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import myproject.ekampus.entities.concretes.Like;

public interface LikeDao extends JpaRepository<Like, Integer> {

}
