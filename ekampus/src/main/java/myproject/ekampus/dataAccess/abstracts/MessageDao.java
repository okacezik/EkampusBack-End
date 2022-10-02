package myproject.ekampus.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import myproject.ekampus.entities.concretes.Message;

public interface MessageDao extends JpaRepository<Message, Integer>{

}
