package myproject.ekampus.business.BusinessRules;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import myproject.ekampus.business.dtos.responses.GetAllStudentsResponse;

public class StudentBusinessRules {

	// This method not using
	public static List<GetAllStudentsResponse> getAllStudentBySorted(List<GetAllStudentsResponse> sortedList) {

		Collections.sort(sortedList, new Comparator<GetAllStudentsResponse>() {
			public int compare(GetAllStudentsResponse student1, GetAllStudentsResponse student2) {
				String firstName = student1.getFirstName();
				String firstName2 = student2.getFirstName();
				return firstName.compareTo(firstName2);
			}
		});

		return sortedList;
	}
}
