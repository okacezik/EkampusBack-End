package myproject.ekampus.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import myproject.ekampus.business.BusinessRules.StudentBusinessRules;
import myproject.ekampus.business.abstracts.StudentService;
import myproject.ekampus.business.dtos.requests.CreateStudentRequest;
import myproject.ekampus.business.dtos.responses.GetAllStudentsResponse;
import myproject.ekampus.core.utilites.mappers.ModelMapperService;
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
@AllArgsConstructor
public class StudentManager implements StudentService {

	private StudentDao studentDao;
	private List<Student> students;
	private ModelMapperService mapperService;

	@Override
	public Result add(CreateStudentRequest createStudentRequest) {
		Student student = this.mapperService.forRequest().map(createStudentRequest, Student.class);
		boolean result = StudentBusinessRules.addStudentControl(students, student);
		if (result) {
			return new ErrorResult(Messages.existStudentForAddOperation);
		} else {
			this.studentDao.save(student);
			return new SuccessResult(Messages.studentAddMessage);
		}
	}

	@Override
	public Result delete(String password, String studentNumber) {

		Student result = StudentBusinessRules.existStudentControl(students, studentNumber, password);
		if (result != null) {
			this.studentDao.delete(result);
			return new SuccessResult(Messages.studentDeleteMessage);
		} else {
			return new ErrorResult(Messages.notFindStudent);
		}
	}

	@Override
	public DataResult<Boolean> entryStudent(String password, String studentNumber) {
		Student result = StudentBusinessRules.existStudentControl(students, studentNumber, password);
		if (result != null) {
			return new SuccessDataResult<Boolean>(true, Messages.welcomeStudent);
		}
		return new ErrorDataResult<Boolean>(false, Messages.wrongUserİnformations);
	}

	@Override
	public DataResult<StudentDetailDto> findByStudentName(String firstName) {
		for (StudentDetailDto student : this.studentDao.getAllStudent()) {
			if (student.getFirstName().equals(firstName)) {
				return new SuccessDataResult<StudentDetailDto>(student, Messages.studentListMessage);
			}
		}
		return new ErrorDataResult<StudentDetailDto>(null, Messages.notFindStudent);
	}

	@Override
	public DataResult<List<StudentDetailDto>> findByStudentNameContains(String studentName) {
		List<StudentDetailDto> students = new ArrayList<StudentDetailDto>();
		for (StudentDetailDto student : this.studentDao.getAllStudent()) {
			if (student.getFirstName().contains(studentName) || student.getLastName().contains(studentName)) {
				students.add(student);
			}
		}
		return new SuccessDataResult<List<StudentDetailDto>>(students, Messages.studentsListMessage);
	}

	/*
	 * @Override public DataResult<List<StudentDetailDto>> getAllStudent() { return
	 * new SuccessDataResult<List<StudentDetailDto>>
	 * (this.studentDao.getAllStudent(),Messages.studentsListMessage); }
	 */
	@Override
	public DataResult<StudentDetailDto> findByStudentId(int id) {
		for (StudentDetailDto student : this.studentDao.getAllStudent()) {
			if (student.getStudentId() == id) {
				return new SuccessDataResult<StudentDetailDto>(student, Messages.studentListMessage);
			}
		}
		return new ErrorDataResult<StudentDetailDto>(null, Messages.notFindStudent);
	}

	@Override
	public DataResult<List<StudentDetailDto>> getByFirstNameStartsWith(String studentName) {
		List<StudentDetailDto> students = new ArrayList<StudentDetailDto>();
		for (StudentDetailDto student : this.studentDao.getAllStudent()) {
			if (student.getFirstName().startsWith(studentName)) {
				students.add(student);
			}
		}
		return new SuccessDataResult<List<StudentDetailDto>>(students, Messages.studentsListMessage);
	}

	@Override
	public DataResult<List<StudentDetailDto>> getAllStudentBySorted() {
		List<StudentDetailDto> sortedList = this.studentDao.getAllStudent();
		return new SuccessDataResult<List<StudentDetailDto>>(StudentBusinessRules.getAllStudentBySorted(sortedList),
				Messages.studentsListMessage);
	}

	@Override
	public DataResult<List<GetAllStudentsResponse>> getAllStudents() {
		List<Student> students = this.studentDao.findAll();
		List<GetAllStudentsResponse> response = students.stream()
				.map(student -> this.mapperService.forResponse().map(student, GetAllStudentsResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllStudentsResponse>>(response, Messages.studentsListMessage);
	}
}
