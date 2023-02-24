package bitcamp.myapp.service;

import java.util.List;
import bitcamp.myapp.vo.Teacher;

public interface TeacherService {

  public void add(Teacher teacher);

  public List<Teacher> list();

  public Teacher get(int no);

  public Teacher get(String email, String password);

  public void update(Teacher teacher);

  public void delete(int no);
}





