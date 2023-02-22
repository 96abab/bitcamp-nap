package bitcamp.myapp.servlet.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bitcamp.myapp.dao.StudentDao;
import bitcamp.myapp.dao.TeacherDao;
import bitcamp.myapp.vo.Member;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private StudentDao studentDao;
  private TeacherDao teacherDao;

  @Override
  public void init() {
    ServletContext ctx = getServletContext();
    studentDao = (StudentDao) ctx.getAttribute("studentDao");
    teacherDao = (TeacherDao) ctx.getAttribute("teacherDao");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String usertype = request.getParameter("usertype");

    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("email", request.getParameter("email"));
    paramMap.put("password", request.getParameter("password"));

    if (request.getParameter("saveEmail") != null) {
      // 다음 로그인 출력할때 이전 입력한 이메이일이 보이게 쿠키로 브라우저에 저장
      Cookie cookie = new Cookie("email", (String) paramMap.get("email"));

      // 쿠키 보존시간을 지정한다
      // => 쿠키 보존 시간을 지정하지 않으면 웹브라우저가 실행되는 동안만 유지
      cookie.setMaxAge(60 * 60 * 24 * 30);// 30일 동안 유지

      // 응답헤더에 추가한다
      response.addCookie(cookie);
    }else {
      // 쿠키에 저장된 이메일 제거하고 싶을때
      Cookie cookie = new Cookie("email", "");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    Member member = null;

    switch (usertype) {
      case "student":
        member = studentDao.findByEmailAndPassword(paramMap);
        break;
      case "teacher":
        member = teacherDao.findByEmailAndPassword(paramMap);
        break;
    }

    if (member != null) {

      // 로그인 사용자 정보를 저장할 세션 객체준비
      // 1 쿠키로 보낸경우
      //    - 세션 id에 해당하는 객체를 찾음
      //        1-1 유효할경우 찾은 세션 객체 리턴 이때 id는 쿠키로 전달 X
      //        1-2 타임아웃되어 유효하지 않는 경우 새로 생성후 id 리턴
      // 2 쿠키로 보내지 않은 경우
      //    - 세션 생성후 리턴
      //    - 새로 생성한 세션 id가 쿠키로 리턴
      HttpSession session = request.getSession();

      // 로그인 사용자 정보 세션에 보관
      session.setAttribute("loginUser", member);

      response.sendRedirect("../"); //http://localhost:8080/web/
      // 웹브라우저에게 전달하는 URL이다.
      // 응답 프로토콜 예:
      //     HTTP/1.1 302
      //     Location: /index.html
      //     ...
      //
    } else {
      request.setAttribute("error", "loginfail");
      request.getRequestDispatcher("/auth/form.jsp").forward(request, response);
    }

  }

}









