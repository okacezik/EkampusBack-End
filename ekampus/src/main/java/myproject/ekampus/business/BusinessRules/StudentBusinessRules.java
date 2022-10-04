package myproject.ekampus.business.BusinessRules;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import myproject.ekampus.entities.concretes.Student;
import myproject.ekampus.entities.dtos.StudentDetailDto;

public class StudentBusinessRules {
	
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
	
	public static List<StudentDetailDto> getAllStudentBySorted(List<StudentDetailDto> sortedList){

		Collections.sort(sortedList,new Comparator<StudentDetailDto>() {
			public int compare(StudentDetailDto student1, StudentDetailDto student2) {
				String firstName = student1.getFirstName();
				String firstName2 = student2.getFirstName();
				return firstName.compareTo(firstName2);
			}
		});
		
		return sortedList;
	}
}
