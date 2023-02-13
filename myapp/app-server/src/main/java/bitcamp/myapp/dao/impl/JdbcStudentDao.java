package bitcamp.myapp.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.dao.StudentDao;
import bitcamp.myapp.vo.Student;

public class JdbcStudentDao implements StudentDao {

  Connection con;

  // 의존객체 Connection 을 생성자에서 받는다.
  public JdbcStudentDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Student s) {
    try (Statement stmt = con.createStatement()) {

      String sql = String.format(
          "insert into app_student(name, tel, pst_no, bas_addr, det_addr, work, gender, level)"
              + " values('%s','%s','%s','%s','%s',%b,'%s',%d)",
              s.getName(),
              s.getTel(),
              s.getPostNo(),
              s.getBasicAddress(),
              s.getDetailAddress(),
              s.isWorking(),
              s.getGender(),
              s.getLevel());

      stmt.executeUpdate(sql);

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public List<Student> findAll() {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select student_id, name, tel, work, level"
                + " from app_student"
                + " order by student_id desc")) {

      ArrayList<Student> list = new ArrayList<>();
      while (rs.next()) {
        Student s = new Student();
        s.setNo(rs.getInt("student_id"));
        s.setName(rs.getString("name"));
        s.setTel(rs.getString("tel"));
        s.setWorking(rs.getBoolean("work"));
        s.setLevel(rs.getByte("level"));

        list.add(s);
      }

      return list;

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Student findByNo(int no) {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select student_id, name, tel, created_date, pst_no, bas_addr, det_addr, work, gender, level"
                + " from app_student"
                + " where student_id=" + no)) {

      if (rs.next()) {
        Student s = new Student();
        s.setNo(rs.getInt("student_id"));
        s.setName(rs.getString("name"));
        s.setTel(rs.getString("tel"));
        s.setCreatedDate(rs.getDate("created_date"));
        s.setPostNo(rs.getString("pst_no"));
        s.setBasicAddress(rs.getString("bas_addr"));
        s.setDetailAddress(rs.getString("det_addr"));
        s.setWorking(rs.getBoolean("work"));
        s.setGender(rs.getString("gender").charAt(0));
        s.setLevel(rs.getByte("level"));
        return s;
      }

      return null;

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public List<Student> findByKeyword(String keyword) {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select student_id, name, tel, work, level"
                + " from app_student"
                + " where name like('%" + keyword + "%')"
                + " or tel like('%" + keyword + "%')"
                + " or bas_addr like('%" + keyword + "%')"
                + " or det_addr like('%" + keyword + "%')"
                + " order by student_id desc")) {

      ArrayList<Student> list = new ArrayList<>();
      while (rs.next()) {
        Student s = new Student();
        s.setNo(rs.getInt("student_id"));
        s.setName(rs.getString("name"));
        s.setTel(rs.getString("tel"));
        s.setWorking(rs.getBoolean("work"));
        s.setLevel(rs.getByte("level"));

        list.add(s);
      }

      return list;

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public int update(Student s) {
    try (Statement stmt = con.createStatement()) {

      String sql = String.format(
          "update app_student set "
              + " name='%s', tel='%s', pst_no='%s', bas_addr='%s', det_addr='%s',"
              + " work=%b, gender='%s', level=%d "
              + " where student_id=%d",
              s.getName(),
              s.getTel(),
              s.getPostNo(),
              s.getBasicAddress(),
              s.getDetailAddress(),
              s.isWorking(),
              s.getGender(),
              s.getLevel(),
              s.getNo());

      return stmt.executeUpdate(sql);

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (Statement stmt = con.createStatement()) {

      String sql = String.format("delete from app_student where student_id=%d", no);

      return stmt.executeUpdate(sql);

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }
}























