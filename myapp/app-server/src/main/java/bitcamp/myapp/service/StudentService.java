package bitcamp.myapp.service;

import java.util.List;
import bitcamp.myapp.vo.Student;

public interface StudentService {


  public void add(Student student);

  public List<Student> list(String keyword);

  public Student get(int no);

  public Student get(String email, String password);

  public void update(Student student);

  public void delete(int no);
}





