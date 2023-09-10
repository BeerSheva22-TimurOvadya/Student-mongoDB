package telran.spring.students;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import telran.spring.students.docs.StudentDoc;
import telran.spring.students.dto.Mark;
import telran.spring.students.dto.Student;
import telran.spring.students.repo.StudentRepository;
import telran.spring.students.service.StudentsService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OldStudentsServiceTests {
	@Autowired
	StudentsService studentsService;

	@Autowired
	StudentRepository studentRepo;

	Student student1 = new Student(123l, "Vasya", "050-1234567");
	Mark mark = new Mark("Java", LocalDate.now(), 90);	

	@Test
	@Order(1)
	void creationDb() {
		assertEquals(student1, studentsService.addStudent(student1));
		studentsService.addMark(123l, mark);
	}
	
	@Test
	@Order(2)
	void readTest() {
		StudentDoc studentDoc = studentRepo.findById(123l).get();
		assertEquals(student1, studentDoc.build());
		assertEquals(mark, studentDoc.getMarks().get(0));
	}

}
