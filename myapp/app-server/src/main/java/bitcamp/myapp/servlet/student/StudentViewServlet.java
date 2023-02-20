package bitcamp.myapp.servlet.student;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import bitcamp.myapp.dao.StudentDao;
import bitcamp.myapp.vo.Student;
import bitcamp.util.BitcampSqlSessionFactory;
import bitcamp.util.DaoGenerator;

@WebServlet("/student/view")
public class StudentViewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private StudentDao studentDao;

  public StudentViewServlet() {
    try {
      InputStream mybatisConfigInputStream = Resources.getResourceAsStream(
          "bitcamp/myapp/config/mybatis-config.xml");
      SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
      BitcampSqlSessionFactory sqlSessionFactory = new BitcampSqlSessionFactory(
          builder.build(mybatisConfigInputStream));
      studentDao = new DaoGenerator(sqlSessionFactory).getObject(StudentDao.class);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int studentNo = Integer.parseInt(request.getParameter("no"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>비트캠프 - NCP 1기</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>학생</h1>");

    Student b = this.studentDao.findByNo(studentNo);

    if (b == null) {
      out.println("<p>해당 번호의 학생이 없습니다.</p>");

    } else { 

      out.println("<form id='student-form' action='update' method='post'>");

      out.println("<table border='1'>");

      out.println("<tr>");
      out.println("  <th>번호</th>");
      out.printf("  <td><input type='text' name='no' value='%d' readonly></td>\n",  b.getNo());
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>이름</th>");
      out.printf("  <td><input type='text' name='name' value='%s'></td>\n",  b.getName());
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>이메일</th>");
      out.printf("  <td><input type='text' name='email' value='%s'></td>\n",  b.getEmail());
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>암호</th>");
      out.println("  <td><input type='password' name='password'></td>");
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>전화</th>");
      out.printf("  <td><input type='text' name='tel' value='%s'></td>\n" , b.getTel());
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>우편번호</th>");
      out.printf("  <td><input type='text' name='postNo' value='%s'></td>\n" , b.getPostNo());
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>주소1</th>");
      out.printf("  <td><input type='text' name='basicAddress' value='%s'></td>\n" , b.getBasicAddress());
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>주소2</th>");
      out.printf("  <td><input type='text' name='detailAddress' value='%s'></td>\n" , b.getDetailAddress());
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>재직</th>");
      out.printf("  <td><input type='text' name='working' value='%s'></td>\n" , b.isWorking() ? "재직중" : "미취업");
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>성별</th>");
      out.printf("  <td><input type='text' name='gender' value='%s'></td>\n" , b.getGender() == '여' ? "여자" : "남자");
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>전공</th>");
      out.printf("  <td><input type='text' name='level' value='%s'></td>\n" , b.getLevel() == 0 ? "비전공자" : b.getLevel() == 1 ? "준전공자" : "전공자");
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <th>등록일</th>");
      out.printf("  <td><input type='text' name='level' value='%s'></td>\n" , b.getCreatedDate());
      out.println("</tr>");


      out.println("</table>");
    }

    out.println("<div>");
    out.println("  <button id='btn-list' type='button'>목록</button>");
    out.println("  <button>변경</button>");
    out.println("  <button id='btn-delete' type='button'>삭제</button>");
    out.println("</div>");

    out.println("</form>");

    out.println("<script>");
    out.println("document.querySelector('#btn-list').onclick = function() {");
    out.println("  location.href = 'list';");
    out.println("}");
    out.println("document.querySelector('#btn-delete').onclick = function() {");
    out.println("  var form = document.querySelector('#student-form');");
    out.println("  form.action = 'delete';");
    out.println("  form.submit();");
    out.println("}");
    out.println("</script>");

    out.println("</body>");
    out.println("</html>");

  }

}
