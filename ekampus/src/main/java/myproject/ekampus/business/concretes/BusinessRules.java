package myproject.ekampus.business.concretes;

import java.util.List;
import myproject.ekampus.entities.concretes.Post;
import myproject.ekampus.entities.concretes.Student;

public class BusinessRules {
	
	public static Student existStudentControl(List<Student> students,String studentNumber, String password) {
		for(Student student : students) {
			if(student.getStudentNumber().equals(studentNumber) && student.getPassword().equals(password)) {
				return student;
			}
		}
		return null;
	}
	
	public static boolean addStudentControl(List<Student> students, Student candidateStudent) {
		for(Student student : students) {
			if(student.getStudentNumber().equals(candidateStudent.getStudentNumber()) || 
					student.getPassword().equals(candidateStudent.getPassword())) {
				return true;
			}
		}
		return false;
	}

	public static Post existPostControl(List<Post> posts, int postId) {
		for(Post post2 : posts) {
			if(post2.getId() == postId) {
				return post2;
			}
		}
		return null;
	}
}
