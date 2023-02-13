package bitcamp.myapp.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bitcamp.myapp.dao.DaoException;
import bitcamp.myapp.dao.TeacherDao;
import bitcamp.myapp.vo.Teacher;

public class JdbcTeacherDao implements TeacherDao {

  Connection con;

  // 의존객체 Connection 을 생성자에서 받는다.
  public JdbcTeacherDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Teacher s) {
    try (Statement stmt = con.createStatement()) {

      String sql = String.format("insert into app_teacher("
          + " name,"
          + " tel,"
          + " email,"
          + " degree,"
          + " school,"
          + " major,"
          + " wage)"
          + " values('%s','%s','%s',%d,'%s','%s',%d)",
          s.getName(),
          s.getTel(),
          s.getEmail(),
          s.getDegree(),
          s.getSchool(),
          s.getMajor(),
          s.getWage());

      stmt.executeUpdate(sql);

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public List<Teacher> findAll() {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select"
            + " m.member_id,"
            + " m.name,"
            + " m.email,"
            + " m.tel,"
            + " t.degree,"
            + " t.major,"
            + " t.wage"
            + " from app_teacher t"
            + "   inner join app_member m on t.member_id = m.member_id"
            + " order by"
            + "   t.name asc")) {

      ArrayList<Teacher> list = new ArrayList<>();
      while (rs.next()) {
        Teacher s = new Teacher();
        s.setNo(rs.getInt("member_id"));
        s.setName(rs.getString("name"));
        s.setEmail(rs.getString("email"));
        s.setTel(rs.getString("tel"));
        s.setDegree(rs.getInt("degree"));
        s.setMajor(rs.getString("major"));
        s.setWage(rs.getInt("wage"));

        list.add(s);
      }

      return list;

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Teacher findByNo(int no) {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select"
            + " m.member_id,"
            + " m.name,"
            + " m.tel,"
            + " m.created_date,"
            + " m.email,"
            + " t.degree,"
            + " t.school,"
            + " t.major,"
            + " t.wage"
            + " from app_teacher t"
            + "   inner join app_member m on t.member_id = m.member_id"
            + " where t.member_id=" + no)) {

      if (rs.next()) {
        Teacher s = new Teacher();
        s.setNo(rs.getInt("member_id"));
        s.setName(rs.getString("name"));
        s.setTel(rs.getString("tel"));
        s.setCreatedDate(rs.getDate("created_date"));
        s.setEmail(rs.getString("email"));
        s.setDegree(rs.getInt("degree"));
        s.setSchool(rs.getString("school"));
        s.setMajor(rs.getString("major"));
        s.setWage(rs.getInt("wage"));
        return s;
      }

      return null;

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public int update(Teacher t) {
    try (Statement stmt = con.createStatement()) {

      String sql = String.format("update app_teacher set "
          + " name='%s',"
          + " tel='%s',"
          + " email='%s',"
          + " degree=%d,"
          + " school='%s',"
          + " major='%s',"
          + " wage=%d "
          + " where member_id=%d",
          t.getName(),
          t.getTel(),
          t.getEmail(),
          t.getDegree(),
          t.getSchool(),
          t.getMajor(),
          t.getWage(),
          t.getNo());

      return stmt.executeUpdate(sql);

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (Statement stmt = con.createStatement()) {

      String sql = String.format("delete from app_teacher"
          + " where member_id=%d", no);

      return stmt.executeUpdate(sql);

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }
}























