package myproject.ekampus.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import myproject.ekampus.business.BusinessRules.StudentBusinessRules;
import myproject.ekampus.business.abstracts.StudentService;
import myproject.ekampus.business.dtos.requests.CreateStudentRequest;
import myproject.ekampus.business.dtos.requests.LogInStudent;
import myproject.ekampus.business.dtos.responses.GetAllStudentsResponse;
import myproject.ekampus.business.dtos.responses.GetByIdStudentResponse;
import myproject.ekampus.core.utilites.mappers.ModelMapperService;
import myproject.ekampus.core.utilites.results.DataResult;
import myproject.ekampus.core.utilites.results.ErrorDataResult;
import myproject.ekampus.core.utilites.results.ErrorResult;
import myproject.ekampus.core.utilites.results.Result;
import myproject.ekampus.core.utilites.results.SuccessDataResult;
import myproject.ekampus.core.utilites.results.SuccessResult;
import myproject.ekampus.dataAccess.abstracts.StudentDao;
import myproject.ekampus.entities.concretes.Student;

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
	public DataResult<GetAllStudentsResponse> findByStudentFirstName(String firstName) {

		Student student = this.studentDao.findByFirstName(firstName);
		return student != null
				? new SuccessDataResult<GetAllStudentsResponse>(
						this.mapperService.forResponse().map(student, GetAllStudentsResponse.class),
						Messages.studentListMessage)
				:

				new ErrorDataResult<>(Messages.notFindStudent);
	}

	@Override
	public DataResult<List<GetAllStudentsResponse>> findByStudentNameContains(String studentName) {

		List<Student> students = this.studentDao.findByFirstNameContains(studentName);
		if (students.size() > 0) {
			List<GetAllStudentsResponse> response = students.stream()
					.map(student -> this.mapperService.forResponse().map(student, GetAllStudentsResponse.class))
					.collect(Collectors.toList());

			return new SuccessDataResult<List<GetAllStudentsResponse>>(response, Messages.studentsListMessage);
		} else {
			return new ErrorDataResult<>(Messages.notFindStudent);
		}

	}

	@Override
	public DataResult<List<GetAllStudentsResponse>> findByFirstNameStartsWith(String studentName) {

		List<Student> students = this.studentDao.findByFirstNameStartsWith(studentName);

		if (students.size() > 0) {
			List<GetAllStudentsResponse> response = students.stream()
					.map(student -> this.mapperService.forResponse().map(student, GetAllStudentsResponse.class))
					.collect(Collectors.toList());

			return new SuccessDataResult<List<GetAllStudentsResponse>>(response, Messages.studentsListMessage);
		} else {
			return new ErrorDataResult<>(Messages.notFindStudent);
		}

	}

	@Override
	public DataResult<List<GetAllStudentsResponse>> getAllStudentBySorted() {
		List<Student> students = this.studentDao.findAll();
		List<GetAllStudentsResponse> response = students.stream()
				.map(student -> this.mapperService.forResponse().map(student, GetAllStudentsResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllStudentsResponse>>(StudentBusinessRules.getAllStudentBySorted(response),
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

	@Override
	public DataResult<GetByIdStudentResponse> getByIdStudent(int id) {
		Optional<Student> student = this.studentDao.findById(id);

		return student.isPresent() == true
				? new SuccessDataResult<GetByIdStudentResponse>(
						this.mapperService.forResponse().map(student, GetByIdStudentResponse.class),
						Messages.studentListMessage)
				: new ErrorDataResult<GetByIdStudentResponse>(Messages.notFindStudent);
	}

	@Override
	public DataResult<GetByIdStudentResponse> findByStudentNumberAndPassword(LogInStudent login) {
		Student student = this.studentDao.findByStudentNumberAndPassword(login.getStudentNumber(), login.getPassword());
		if (student != null) {
			return new SuccessDataResult<GetByIdStudentResponse>(
					this.mapperService.forResponse().map(student, GetByIdStudentResponse.class),
					Messages.studentListMessage);
		} else {
			return new ErrorDataResult<GetByIdStudentResponse>(Messages.notFindStudent);
		}
	}

	@Override
	public DataResult<GetAllStudentsResponse> getByStudentNumberStudent(String studentNumber) {
		Student student = this.studentDao.findByStudentNumber(studentNumber);
		return student != null
				? new SuccessDataResult<GetAllStudentsResponse>(
						this.mapperService.forResponse().map(student, GetAllStudentsResponse.class),
						Messages.studentListMessage)
				: new ErrorDataResult<GetAllStudentsResponse>(Messages.notFindStudent);
	}
}
