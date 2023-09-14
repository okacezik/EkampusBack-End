package myproject.ekampus.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import myproject.ekampus.business.abstracts.StudentService;
import myproject.ekampus.business.dtos.requests.CreateStudentRequest;
import myproject.ekampus.business.dtos.requests.DeleteStudentRequest;
import myproject.ekampus.business.dtos.requests.HiddenAccountRequest;
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
	private ModelMapperService mapperService;

	@Override
	public DataResult<List<GetAllStudentsResponse>> add(CreateStudentRequest createStudentRequest) {
		Student student = this.studentDao.findByStudentNumber(createStudentRequest.getStudentNumber());

		if (student == null) {
			student = this.mapperService.forRequest().map(createStudentRequest, Student.class);
			this.studentDao.save(student);
			return this.getAllStudents();
		}

		return new ErrorDataResult<>(Messages.existStudentForAddOperation);
	}

	@Override
	public Result delete(DeleteStudentRequest deleteStudentRequest) {

		Student student = this.studentDao.findByStudentNumber(deleteStudentRequest.getStudentNumber());
		if (student == null) {
			return new ErrorResult(Messages.notFindStudent);
		} else {
			this.studentDao.delete(student);
			return new SuccessResult(Messages.studentDeleteMessage);
		}
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
		}
		return new ErrorDataResult<>(Messages.notFindStudent);

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

		Sort sort = Sort.by(Direction.ASC, "firstName");

		List<Student> students = this.studentDao.findAll(sort);
		List<GetAllStudentsResponse> response = students.stream()
				.map(student -> this.mapperService.forResponse().map(student, GetAllStudentsResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllStudentsResponse>>(response, Messages.studentsListMessage);
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

	@Override
	public Result hiddenAccountRequest(HiddenAccountRequest hiddenAccountRequest) {

		Student student = this.studentDao.findByStudentNumber(hiddenAccountRequest.getStudentNumber());
		student.setHiddenAccount(true);
		this.studentDao.save(student);
		return new SuccessResult(Messages.handleHiddenAccount);
	}

	@Override
	public DataResult<Boolean> isHiddenAccountByStudentNumber(String studentNumber) {
		Student student = this.studentDao.findByStudentNumber(studentNumber);

		return new SuccessDataResult<Boolean>(student.isHiddenAccount(), studentNumber);
	}

}
