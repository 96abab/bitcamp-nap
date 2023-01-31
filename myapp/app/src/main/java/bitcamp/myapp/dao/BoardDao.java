package bitcamp.myapp.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import bitcamp.myapp.vo.Board;

public class BoardDao {

  List<Board> list;
  int lastNo;

  public BoardDao(List<Board> list) {
    this.list = list;
  }

  public void insert(Board board) {
    board.setNo(++lastNo);
    board.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    list.add(board);
  }

  public Board[] findAll() {
    Board[] boards = new Board[list.size()];
    Iterator<Board> i = list.iterator();
    int index = 0;
    while (i.hasNext()) {
      boards[index++] = i.next();
    }
    return boards;
  }

  public Board findByNo(int no) {
    Board b = new Board();
    b.setNo(no);

    int index = list.indexOf(b);
    if (index == -1) {
      return null;
    }

    return list.get(index);
  }

  public void update(Board b) {
    int index = list.indexOf(b);
    list.set(index, b);
  }

  public boolean delete(Board b) {
    return list.remove(b);
  }

  public void save(String filename) {
    try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
      out.flush();

      // 2) 게시글 개수를 출력 : 4byte
      //      for (Board b : out) {
      //        out.write(b + System.out.append(filename));
      //      }

      //      out.flush();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @SuppressWarnings("unchecked")
  public void load(String filename) {
    if (list.size() > 0) { // 중복 로딩 방지!
      return;
    }

    try (BufferedReader out = new BufferedReader(new FileReader(filename))) {
      list = (List<Board>) out;


      //      String s1;
      //      while ((s1 = out.readLine()) != null) {
      //        System.out.println(s1);

      // 2) 저장된 게시글 개수를 읽는다: 4byte
      //
      //      if (list.size() > 0) {
      //        lastNo = list.get(list.size() - 1).getNo();
      //      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}























