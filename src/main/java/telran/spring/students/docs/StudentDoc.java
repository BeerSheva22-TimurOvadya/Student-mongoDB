package telran.spring.students.docs;

import java.util.*;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NonNull;
import telran.spring.students.dto.Mark;
import telran.spring.students.dto.Student;

@Document(collection = "students")
@Data

public class StudentDoc {

	final long id;
	@NonNull
	String name;
	@NonNull
	String phone;
	List<Mark> marks = new ArrayList<>();

	public static StudentDoc of(Student student) {
		return new StudentDoc(student.id(), student.name(), student.phone());
	}

	public Student build() {
		return new Student(id, name, phone);
	}

}
