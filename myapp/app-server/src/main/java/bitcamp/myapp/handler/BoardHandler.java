package bitcamp.myapp.handler;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;
import bitcamp.util.StreamTool;

public class BoardHandler {

  private BoardDao boardDao;
  private String title;

  public BoardHandler(String title, BoardDao boardDao) {
    this.title = title;
    this.boardDao = boardDao;
  }

  private void inputBoard(StreamTool streamTool) throws Exception {
    Board b = new Board();
    b.setTitle(streamTool.promptString("제목? "));
    b.setContent(streamTool.promptString("내용? "));
    b.setPassword(streamTool.promptString("암호? "));

    this.boardDao.insert(b);
    streamTool.send();
  }

  private void printBoards(StreamTool streamTool) throws Exception {
    streamTool.println("번호\t제목\t작성일\t조회수");

    Board[] boards = this.boardDao.findAll();

    for (Board b : boards) {
      streamTool.printf("%d\t%s\t%s\t%d\n",
          b.getNo(), b.getTitle(), b.getCreatedDate(), b.getViewCount());
    }

    streamTool.send();

  }

  private void printBoard(StreamTool streamTool) throws Exception {
    int boardNo = streamTool.promptInt("게시글 번호? ");

    Board b = this.boardDao.findByNo(boardNo);

    if (b == null) {
      streamTool.println("해당 번호의 게시글 없습니다.").send();
      return;
    }

    streamTool.printf("    제목: %s\n", b.getTitle())
    .printf("    내용: %s\n", b.getContent())
    .printf("  등록일: %s\n", b.getCreatedDate())
    .printf("  조회수: %d\n", b.getViewCount());
    b.setViewCount(b.getViewCount() + 1);
    streamTool.send();
  }

  private void modifyBoard() {
    int boardNo = Prompt.inputInt("게시글 번호? ");

    Board old = this.boardDao.findByNo(boardNo);

    if (old == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    // 변경할 데이터를 저장할 인스턴스 준비
    Board b = new Board();
    b.setNo(old.getNo());
    b.setCreatedDate(old.getCreatedDate());
    b.setTitle(Prompt.inputString(String.format("제목(%s)? ", old.getTitle())));
    b.setContent(Prompt.inputString(String.format("내용(%s)? ", old.getContent())));
    b.setPassword(Prompt.inputString("암호? "));
    b.setViewCount(old.getViewCount());

    if (!old.getPassword().equals(b.getPassword())) {
      System.out.println("암호가 맞지 않습니다!");
      return;
    }

    String str = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (str.equalsIgnoreCase("Y")) {
      this.boardDao.update(b);
      System.out.println("변경했습니다.");
    } else {
      System.out.println("변경 취소했습니다.");
    }

  }

  private void deleteBoard() {
    int boardNo = Prompt.inputInt("게시글 번호? ");

    Board b = this.boardDao.findByNo(boardNo);

    if (b == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String password = Prompt.inputString("암호? ");
    if (!b.getPassword().equals(password)) {
      System.out.println("암호가 맞지 않습니다!");
      return;
    }

    String str = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!str.equalsIgnoreCase("Y")) {
      System.out.println("삭제 취소했습니다.");
      return;
    }

    this.boardDao.delete(b);

    System.out.println("삭제했습니다.");

  }

  private void searchBoard() {
    Board[] boards = this.boardDao.findAll();

    String keyword = Prompt.inputString("검색어? ");
    System.out.println("번호\t제목\t작성일\t조회수");

    for (Board b : boards) {
      if (b.getTitle().indexOf(keyword) != -1 ||
          b.getContent().indexOf(keyword) != -1) {
        System.out.printf("%d\t%s\t%s\t%d\n",
            b.getNo(), b.getTitle(), b.getCreatedDate(), b.getViewCount());
      }
    }
  }

  public void service(StreamTool streamTool) throws Exception {

    menu(streamTool);

    while (true) {
      String command = streamTool.readString();

      if (command.equals("menu")) {
        menu(streamTool);
        continue;
      }
      int menuNo;

      try {
        menuNo = Integer.parseInt(command);
      }catch (Exception e) {
        streamTool.println("메뉴가 번호 옳지 않습니다").println().send();
        continue;
      }

      try {
        switch (menuNo) {
          case 0:
            streamTool.println().send();
            return;
          case 1: this.inputBoard(streamTool); break;
          case 2: this.printBoards(streamTool); break;
          case 3: this.printBoard(streamTool); break;
          case 4: this.modifyBoard(); break;
          case 5: this.deleteBoard(); break;
          case 6: this.searchBoard(); break;
          default:
            streamTool.println("잘못된 메뉴 번호 입니다.").send();
        }
      }catch (Exception e) {
        streamTool.printf("명령 실행중 오류 발생 - %s : %s\n",
            e.getMessage(),
            e.getClass().getSimpleName()).send();

      }
    }
  }
  void menu(StreamTool streamTool) throws Exception {
    streamTool.printf("[%s]\n", this.title)
    .println("1. 등록")
    .println("2. 목록")
    .println("3. 조회")
    //    .println("4. 변경")
    //    .println("5. 삭제")
    //    .println("6. 검색")
    //    .println("0. 이전")
    .send();
  }
}
