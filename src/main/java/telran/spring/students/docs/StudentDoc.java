package telran.spring.students.docs;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import telran.spring.students.dto.Mark;

@Document
public class StudentDoc {
long id;
String name;
String phone;
List<Mark> marks;
}
