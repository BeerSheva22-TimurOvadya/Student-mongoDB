package telran.spring.students.service;

import java.time.LocalDate;
import java.util.List;

import telran.spring.students.docs.StudentDoc;
import telran.spring.students.dto.*;

public interface StudentsService {
	Student addStudent(Student student);

	void addMark(long studentId, Mark mark);

	List<Mark> getMarksStudentSubject(long studentId, String subject);

	List<Mark> getMarksStudentDates(long studentId, LocalDate date1, LocalDate date2);

	List<Student> getStudentsPhonePrefix(String phonePrefix);

	List<IdName> getStudentsAllScoresGreater(int score);

	List<Long> removeStudentsWithFewMarks(int nMarks);

	double getStudentsAvgScore();

	// students having avg scores greater than the good mark threshold
	List<IdName> getGoodStudents();

	List<IdName> getStudentsAvgMarkGreater(int score);

	List<IdNameMarks> findStudents(String jsonQuery);
	
	List<IdNameMarks> getBestStudents(int nStudents); //nStudents - best students(best criteria is sum of all student marks
	
	List<IdNameMarks> getWorstStudents(int nStudents); //nStudents - worst students by the same criteria as best
	
	List<IdNameMarks> getBestStudentsSubject(int nStudents, String subject);
	
	List<MarksBucket> scoresDistribution(int nBuckets);//MinMaxCount
	
}
