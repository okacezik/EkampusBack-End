package myproject.ekampus.business.concretes;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import myproject.ekampus.business.abstracts.StudentService;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.ErrorDataResult;
import myproject.ekampus.core.utilites.results.ErrorResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.core.utilites.results.SuccessDataResult;
import myproject.ekampus.core.utilites.results.SuccessResult;
import myproject.ekampus.dataAccess.abstracts.StudentDao;
import myproject.ekampus.entities.concretes.Student;
import myproject.ekampus.entities.dtos.StudentDetailDto;

@Service
public class StudentManager implements StudentService{
	
	private StudentDao studentDao;
	private List<Student> students;
	
	@Autowired
	public StudentManager(StudentDao studentDao) {
		this.studentDao = studentDao;
		this.students = this.studentDao.findAll();
	}

	@Override
	public Result add(Student student) {
		
		boolean result = BusinessRules.addStudentControl(students, student);
		if(result) {
			return new ErrorResult(Messages.existStudentForAddOperation);
		}else {
			this.studentDao.save(student);
			return new SuccessResult(Messages.studentAddMessage);	
		}
	}

	@Override
	public Result delete(String password, String studentNumber) {
		
		Student result = BusinessRules.existStudentControl(students, studentNumber, password);
		if(result != null) {
			this.studentDao.delete(result);
			return new SuccessResult(Messages.studentDeleteMessage);
		}else {
			return new ErrorResult(Messages.notFindStudent);
		}
	}

	/*
	@Override
	public DataResult<List<Student>> getAll() {
		return new SuccessDataResult<List<Student>>(this.studentDao.findAll(), "Students listed...");
	}
	 */
	
	@Override
	public DataResult<Boolean> entryStudent(String password, String studentNumber) {
		Student result = BusinessRules.existStudentControl(students, studentNumber, password);
		if(result != null) {
			return new SuccessDataResult<Boolean>(true,Messages.welcomeStudent);
		}
		return new ErrorDataResult<Boolean>(false,Messages.wrongUserİnformations);
	}

	@Override
	public DataResult<StudentDetailDto> findByStudentName(String firstName) {
		for(StudentDetailDto student : this.studentDao.getAllStudent()) {
			if(student.getFirstName().equals(firstName)) {
				return new SuccessDataResult<StudentDetailDto>
					(student, Messages.studentListMessage);
			}
		}
		return new ErrorDataResult<StudentDetailDto>(null, Messages.notFindStudent);
	}

	@Override
	public DataResult<List<StudentDetailDto>> findByStudentNameContains(String studentName) {
		List<StudentDetailDto> students = new ArrayList<StudentDetailDto>();
		for(StudentDetailDto student : this.studentDao.getAllStudent()) {
			if(student.getFirstName().contains(studentName) || student.getLastName().contains(studentName)) {
				students.add(student);
			}
		}
		return new SuccessDataResult<List<StudentDetailDto>>
			(students,Messages.studentsListMessage);
	}

	@Override
	public DataResult<List<StudentDetailDto>> getAllStudent() {
		return new SuccessDataResult<List<StudentDetailDto>>
		(this.studentDao.getAllStudent(),Messages.studentsListMessage);
	}

	@Override
	public DataResult<StudentDetailDto> findByStudentId(int id) {
		for(StudentDetailDto student :this.studentDao.getAllStudent()) {
			if(student.getStudentId() == id) {
				return new SuccessDataResult<StudentDetailDto>
					(student, Messages.studentListMessage);
			}
		}
		return new ErrorDataResult<StudentDetailDto>
			(null, Messages.notFindStudent);
	}

	@Override
	public DataResult<List<StudentDetailDto>> getByFirstNameStartsWith(String studentName) {
		List<StudentDetailDto> students = new ArrayList<StudentDetailDto>();
		for(StudentDetailDto student : this.studentDao.getAllStudent()) {
			if(student.getFirstName().startsWith(studentName)) {
				students.add(student);
			}
		}
		return new SuccessDataResult<List<StudentDetailDto>>
			(students,Messages.studentsListMessage);
	}


}
