package bitcamp.myapp;

import java.sql.Date;

public class BoardHandler {
	
  static final int SIZE = 100;
  int count;
  Board[] boards = new Board[SIZE];
  String title;

  BoardHandler(String title) {
    this.title = title;
  }

  void inputBoard() {  // 1. 입력
    Board b = new Board();
    b.no = Prompt.inputInt("번호? ");
    b.title = Prompt.inputString("제목? ");
    b.content = Prompt.inputString("내용? ");
    b.passwd = Prompt.inputInt("암호? ");
    b.createdDate = new Date(System.currentTimeMillis()).toString();
    this.boards[count++] = b;
  }

  void printBoards() {  // 2. 목록 
    System.out.println("번호\t제목\t작성일\t조회수");

    for (int i = 0; i < this.count; i++) {
      Board b = this.boards[i];
      System.out.printf("%d\t%s\t%s\t%s\n",
          b.no, b.title, b.createdDate,b.viewno);
    }
  }

  void printBoard() {   // 3.조회
    int memberNo = Prompt.inputInt("게시글 번호? ");
    
    Board b = this.findByNo(memberNo);

    if (b == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    } else {
    	b.viewno += 1;
    }
    System.out.printf("    제목: %s\n", b.title);
    System.out.printf("    내용: %s\n", b.content);
    System.out.printf("  작성일: %s\n", b.createdDate);
    System.out.printf("  조회수: %s\n", b.viewno);
  }

  void modifyBoard() { // 4. 변경
    int memberNo = Prompt.inputInt("게시글 번호? ");
    int memberPasswd = Prompt.inputInt("암호를 입력하시오! ");
    
    Board old = this.findByNo(memberNo);
    Board old2 = this.findByNo(memberPasswd);
    
    if (old == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }else if (old2.passwd != old.passwd) {
    	System.out.println("입력하신 정보가 일치하지 않습니다.");
    	return;
    }
    
    Board b = new Board();
    b.no = old.no;
    b.viewno = old.viewno;
    b.passwd = old.passwd;
    b.createdDate = old.createdDate;
    b.title = Prompt.inputString(String.format("제목(%s)? ", old.title));
    b.content = Prompt.inputString(String.format("내용(%s)? ", old.content));
    String str = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (str.equalsIgnoreCase("Y")) {
      this.boards[indexOf(old)] = b;
      System.out.println("변경했습니다.");
    } else {
      System.out.println("변경 취소했습니다.");
    }
  }

  void deleteBoard() { // 5. 삭제
    int memberNo = Prompt.inputInt("게시글 번호? ");

    Board b = findByNo(memberNo);

    if (b == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String str = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!str.equalsIgnoreCase("Y")) {
      System.out.println("삭제 취소했습니다.");
      return;
    }

    for (int i = indexOf(b) + 1; i < this.count; i++) {
      this.boards[i - 1] = this.boards[i];
    }
    this.boards[--this.count] = null;
    System.out.println("삭제했습니다.");
  }

  Board findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.boards[i].no == no) {
        return this.boards[i];
      }
    }
    return null;
  }

  int indexOf(Board b) {
    for (int i = 0; i < this.count; i++) {
      if (this.boards[i].no == b.no) {
        return i;
      }
    }
    return -1;
  }

  void service() {
    while (true) {
      System.out.printf("[%s]\n", this.title);
      System.out.println("1. 입력");
      System.out.println("2. 목록");
      System.out.println("3. 조회");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("0. 이전");
      int menuNo = Prompt.inputInt(String.format("%s> ", this.title));

      switch (menuNo) {
        case 0: return;
        case 1: this.inputBoard(); break;
        case 2: this.printBoards(); break;
        case 3: this.printBoard(); break;
        case 4: this.modifyBoard(); break;
        case 5: this.deleteBoard(); break;
        default:
          System.out.println("잘못된 메뉴 번호 입니다.");
      }
    }
  }
}
