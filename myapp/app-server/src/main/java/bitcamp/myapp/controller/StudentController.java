package bitcamp.myapp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.ResponseBody;
>>>>>>> d44f4ef67bc9387fe5d07c85f4fd5db4a0283b03
import bitcamp.myapp.service.StudentService;
import bitcamp.myapp.vo.Student;
import bitcamp.util.RestResult;
import bitcamp.util.RestStatus;

<<<<<<< HEAD
@RestController
@RequestMapping("/students")
=======
@Controller
>>>>>>> d44f4ef67bc9387fe5d07c85f4fd5db4a0283b03
public class StudentController {

  Logger log = LogManager.getLogger(getClass());

  {
    log.trace("StudentController 생성됨!");
  }

  @Autowired private StudentService studentService;

<<<<<<< HEAD
  @PostMapping
  public Object insert(@RequestBody Student student) {
=======
  @GetMapping("/student/form")
  public void form() {
  }

  @PostMapping("/student/insert")
  public void insert(Student student, Model model) {
>>>>>>> d44f4ef67bc9387fe5d07c85f4fd5db4a0283b03
    studentService.add(student);
    return new RestResult()
        .setStatus(RestStatus.SUCCESS);
  }

<<<<<<< HEAD
  @GetMapping
=======
  @GetMapping("/students")
  @ResponseBody
>>>>>>> d44f4ef67bc9387fe5d07c85f4fd5db4a0283b03
  public Object list(String keyword) {
    return new RestResult()
        .setStatus(RestStatus.SUCCESS)
        .setData(studentService.list(keyword));
  }

<<<<<<< HEAD
  @GetMapping("{no}")
=======
  @GetMapping("/students/{no}")
  @ResponseBody
>>>>>>> d44f4ef67bc9387fe5d07c85f4fd5db4a0283b03
  public Object view(@PathVariable int no) {
    return new RestResult()
        .setStatus(RestStatus.SUCCESS)
        .setData(studentService.get(no));
  }

<<<<<<< HEAD
  @PutMapping("{no}")
=======
  @PutMapping("/students/{no}")
  @ResponseBody
>>>>>>> d44f4ef67bc9387fe5d07c85f4fd5db4a0283b03
  public Object update(
      @PathVariable int no,
      @RequestBody Student student) {

<<<<<<< HEAD
    log.debug(student);

    // 보안을 위해 URL 번호를 게시글 번호로 설정한다.
    student.setNo(no);

=======
    System.out.println(student);
>>>>>>> d44f4ef67bc9387fe5d07c85f4fd5db4a0283b03
    studentService.update(student);
    return new RestResult()
        .setStatus(RestStatus.SUCCESS);
  }

<<<<<<< HEAD
  @DeleteMapping("{no}")
  public Object delete(@PathVariable int no) {
=======
  @PostMapping("/student/delete")
  public void delete(int no, Model model) {
>>>>>>> d44f4ef67bc9387fe5d07c85f4fd5db4a0283b03
    studentService.delete(no);
    return new RestResult()
        .setStatus(RestStatus.SUCCESS);
  }
}
