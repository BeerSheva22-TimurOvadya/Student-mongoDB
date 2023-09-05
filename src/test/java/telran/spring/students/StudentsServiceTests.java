package telran.spring.students;



import java.util.List;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static telran.spring.students.TestDbCreation.*;

import telran.spring.students.docs.StudentDoc;
import telran.spring.students.dto.Mark;
import telran.spring.students.dto.SubjectMark;
import telran.spring.students.service.StudentsService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentsServiceTests{
	@Autowired
	StudentsService studentsService;

	@Autowired
	TestDbCreation testDbCreation;
	@BeforeEach
	void setUp() {
		testDbCreation.createDb();
	}
	

	
	@Test
	void studentSubjectMarks() {
		List<Mark> marks = studentsService.getMarksStudentSubject(ID1, SUBJECT1);
		assertEquals(2, marks.size());
		
		assertEquals(80, marks.get(0).score());
		assertEquals(90, marks.get(1).score());
		
		assertEquals( SUBJECT1, marks.get(0).subject());		
		assertEquals( SUBJECT1, marks.get(1).subject());
		
	}
	
	@Test
	void studentDatesMarkTest() {
		List<Mark> marks = studentsService.getMarksStudentDates(ID1, DATE2, DATE3);
		assertEquals(2, marks.size());
		assertEquals(70, marks.get(0).score());
		assertEquals(90, marks.get(1).score());
		marks = studentsService.getMarksStudentDates(ID4, DATE2, DATE3);
		assertTrue(marks.isEmpty());
	}
	
	@Test
	void studentsPhonePrefixTest() {
		List<StudentDoc> students = studentsService.getStudentsPhonePrefix("050");
		assertEquals(3, students.size());
		StudentDoc student2 = students.get(0);
		assertNull(student2.getMarks());
		assertEquals(ID2, student2.getId());
		students.forEach(s -> assertTrue(s.getPhone().startsWith("050")));
		
	}

}