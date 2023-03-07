package bitcamp.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 페이지 컨트롤러의 공통 설정을 수행하는 클래스
@ControllerAdvice
public class GlobalControllerAdvice {

  Logger log = LogManager.getLogger(getClass());

  {
    log.trace("GlobalControllerAdvice");
  }

  @ExceptionHandler
  public String handle(Exception e, HttpServletRequest request, Model model) {
    log.debug(request.getRequestURI() + "요청처리중 오류 발생", e);
    model.addAttribute("url", request.getRequestURI());
    model.addAttribute("class", e.getClass().getName());
    model.addAttribute("message", e.getMessage());
    return "error";
  }
}
