package bitcamp.myapp.servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import com.google.gson.Gson;
import bitcamp.myapp.dao.TeacherDao;
import bitcamp.myapp.vo.Teacher;

public class TeacherServlet {

  private TeacherDao teacherDao;

  public TeacherServlet(TeacherDao teacherDao) {
    this.teacherDao = teacherDao;
  }

  private void oninputTeacher(DataInputStream in, DataOutputStream out) throws Exception {
    Teacher m = new Gson().fromJson(in.readUTF(), Teacher.class);
    this.teacherDao.insert(m);
    out.writeUTF("200");
    out.writeUTF("success");

  }

  private void onprintTeachers(DataInputStream in, DataOutputStream out) throws Exception {
    out.writeUTF("200");
    out.writeUTF(new Gson().toJson(this.teacherDao.findAll()));
  }

  private void onprintTeacher(DataInputStream in , DataOutputStream out) throws Exception {

    int teacherNo = new Gson().fromJson(in.readUTF(), int.class);

    Teacher m = this.teacherDao.findByNo(teacherNo);
    if (m == null) {
      out.writeUTF("400");
      return;
    }
    out.writeUTF("200");
    out.writeUTF(new Gson().toJson(m));
  }

  private static String getDegreeText(int degree) {// 수정?
    switch (degree) {
      case 1: return "고졸";
      case 2: return "전문학사";
      case 3: return "학사";
      case 4: return "석사";
      case 5: return "박사";
      default: return "기타";
    }
  }

  private void onmodifyTeacher(DataInputStream in, DataOutputStream out) throws Exception {
    Teacher teacher = new Gson().fromJson(in.readUTF(), Teacher.class);

    Teacher old = this.teacherDao.findByNo(teacher.getNo());
    if (old == null) {
      out.writeUTF("400");
      return;
    }
    this.teacherDao.update(teacher);
    out.writeUTF("200");
    out.writeUTF("success");
  }


  private void ondeleteTeacher(DataInputStream in, DataOutputStream out) throws Exception {
    Teacher teacher = new Gson().fromJson(in.readUTF(), Teacher.class);

    Teacher m = this.teacherDao.findByNo(teacher.getNo());
    if (m == null) {
      out.writeUTF("400");
      return;
    }
    this.teacherDao.delete(m);
    out.writeUTF("200");
    out.writeUTF("success");
  }

  public void service(DataInputStream in, DataOutputStream out) throws Exception {
    try {

      String action = in.readUTF();

      switch (action) {
        case "inputTeacher": this.oninputTeacher(in, out); break;
        case "printTeachers": this.onprintTeachers(in, out); break;
        case "printTeacher": this.onprintTeacher(in, out); break;
        case "modifyTeacher": this.onmodifyTeacher(in, out); break;
        case "deleteTeacher": this.ondeleteTeacher(in, out); break;
        default:
          System.out.println("잘못된 메뉴 번호 입니다.");
      }
    }catch (Exception e) {
      out.writeUTF("500");
    }
  }
}
