package bitcamp.myapp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.myapp.vo.Member;

@WebFilter("/*")
public class AuthFilter implements Filter {
  // 요청 데이터를 꺼내기 전에, 즉 getParameter()를 호출하기 전에
  // 클라이언트가 보낸 값이 어떤 문자집합으로 인코딩되었는지 알려준다.
  // POST 요청으로 한글 데이터가 들어 왔을 때 한글을 깨뜨리지 않고 꺼내는 방법

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    String contextRoot = httpRequest.getContextPath();
    String servletPath = httpRequest.getServletPath().toLowerCase();
    if(servletPath.endsWith("insert") ||
        servletPath.endsWith("update") ||
        servletPath.endsWith("delete")) {

      Member loginUser = (Member) httpRequest.getSession().getAttribute("loginUser");
      if (loginUser == null) {
        httpResponse.sendRedirect(contextRoot + "/auth/form");
        return;
      }
    }

    chain.doFilter(request, response); // 다음 필터 실행. 다음 필터가 없으면 최종 목적지인 서블릿 실행.
  }
}
