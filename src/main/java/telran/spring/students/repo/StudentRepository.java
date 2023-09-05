package telran.spring.students.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.spring.students.docs.*;
import telran.spring.students.dto.SubjectMark;

public interface StudentRepository extends MongoRepository<StudentDoc, Long> {
	SubjectMark findByIdAndMarksSubjectEquals(Long id, String subject);

	SubjectMark findByIdAndMarksDateBetween(Long id, LocalDate date1, LocalDate date2);

	@Query(value = "{phone:{$regex:/^?0/}}", fields = "{phone:1, name:1}")
	List<StudentDoc> findStudentsPhonePrefix(String phonePrefix);

}
