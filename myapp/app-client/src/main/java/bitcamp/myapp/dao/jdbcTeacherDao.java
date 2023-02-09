package bitcamp.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import bitcamp.myapp.vo.Teacher;

public class jdbcTeacherDao implements TeacherDao {

  @Override
  public void insert(Teacher t) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study","1111");
        Statement stmt = con.createStatement()) {

      String sql = String.format("insert into app_teacher(name, tel, email, degree, school, major, wage) values('%s','%s','%s','%s','%s','%s','%s')",
          t.getName(),t.getTel(),t.getEmail(),t.getDegree(),t.getSchool(),t.getMajor(),t.getWage());
      stmt.executeUpdate(sql);

    }catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Teacher[] findAll() {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study","1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select teacher_id, name, tel, degree, created_date, major, wage from app_teacher order by teacher_id desc")) {

      ArrayList<Teacher> list = new ArrayList<>();
      while (rs.next()) {
        Teacher t = new Teacher();
        t.setNo(rs.getInt("teacher_id"));
        t.setName(rs.getString("name"));
        t.setTel(rs.getString("tel"));
        t.setDegree(rs.getInt("degree"));
        t.setCreatedDate(rs.getString("created_date"));
        t.setMajor(rs.getString("major"));
        t.setWage(rs.getInt("wage"));


        list.add(t);
      }
      Teacher[] teachers = new Teacher[list.size()];
      list.toArray(teachers);
      return teachers;

      //      return list.toArray(new Teacher[] {});
    }catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Teacher findByNo(int no) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study","1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select teacher_id, name,tel,email,created_date,degree,school,major,wage from app_teacher order by teacher_id desc")) {

      ArrayList<Teacher> list = new ArrayList<>();
      while (rs.next()) {
        Teacher t = new Teacher();
        t.setNo(rs.getInt("teacher_id"));
        t.setName(rs.getString("name"));
        t.setTel(rs.getString("tel"));
        t.setEmail(rs.getString("email"));
        t.setCreatedDate(rs.getString("created_date"));
        t.setDegree(rs.getInt("degree"));
        t.setSchool(rs.getString("school"));
        t.setMajor(rs.getString("major"));
        t.setWage(rs.getInt("wage"));


        return t;
      }
      return null;

      //      return list.toArray(new Teacher[] {});
    }catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void update(Teacher t) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study","1111");
        Statement stmt = con.createStatement()) {

      String sql = String.format("update app_teacher set name='%s', tel='%s', email='%s', degree='%s', school='%s', major='%s', wage='%s' where teacher_id=%d",
          t.getName(),t.getTel(),t.getEmail(),t.getDegree(),t.getSchool(),t.getMajor(),t.getWage());
      stmt.executeUpdate(sql);

    }catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public boolean delete(Teacher t) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb", "study","1111");
        Statement stmt = con.createStatement()) {

      String sql = String.format("delete from app_teacher where teacher_id=%d", t.getNo());

      return stmt.executeUpdate(sql) == 1;

    }catch (Exception e) {
      throw new DaoException(e);
    }
  }

}























