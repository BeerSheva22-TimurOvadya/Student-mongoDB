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
import telran.spring.students.dto.IdName;
import telran.spring.students.dto.Mark;
import telran.spring.students.dto.SubjectMark;
import telran.spring.students.repo.StudentRepository;
import telran.spring.students.service.StudentsService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentsServiceTests{
	@Autowired
	StudentsService studentsService;

	@Autowired
	TestDbCreation testDbCreation;
	
	@Autowired
	StudentRepository studentRepo;
	
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
	
	@Test
	void studentsAllMarksGreaterTest() {
		List<IdName> students = studentsService.getStudentsAllScoresGreater(70);
		assertEquals(2, students.size());
		
		IdName studentDocId3 = students.get(0);		
		assertEquals(ID3, studentDocId3.getId());
		assertEquals("name3", studentDocId3.getName());
		
		IdName studentDocId5 = students.get(1);				
		assertEquals(ID5, studentDocId5.getId());
		assertEquals("name5", studentDocId5.getName());			
	}
	
	@Test
	void studentsFewMarksTest() {
		List<Long> ids = studentsService.removeStudentsWithFewMarks(2);
		assertEquals(2, ids.size());
		assertEquals(ID4, ids.get(0));
		assertEquals(ID6, ids.get(1));
		assertNull(studentRepo.findById(ID4).orElse(null));
		assertNull(studentRepo.findById(ID6).orElse(null));
		
	}	

}