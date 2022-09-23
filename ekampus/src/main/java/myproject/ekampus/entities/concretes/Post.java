package myproject.ekampus.entities.concretes;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	/*
	*@Column(name = "owner_id")
	*private int ownerId;
	*/
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "photo_path")
	private String postPhotoPath;
	
	@Column(name = "date")
	private LocalDateTime loadDate = LocalDateTime.now();
	
	@ManyToOne()
	@JoinColumn(name = "student_id")
	private Student student;
}
