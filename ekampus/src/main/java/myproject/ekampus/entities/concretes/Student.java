package myproject.ekampus.entities.concretes;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students") 
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private int id;
	
	@Column(name = "student_number")
	private String studentNumber;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "departmant")
	private String departmantName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "student_photo")
	private String studentPhotoPath;
	
	@OneToMany(mappedBy = "student")
	private List<Post> posts;
	
	@Column(name = "hidden_account")
	private boolean hiddenAccount=false;
	
}
