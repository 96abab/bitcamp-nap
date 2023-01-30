package bitcamp.myapp.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import bitcamp.myapp.vo.Board;
import bitcamp.util.BinaryDecoder;
import bitcamp.util.BinaryEncoder;

public class BoardDao {

  // 특정 클래스를 지정하기 보다는
  // 인터페이스를 통해 관계를 느슨하게 만든다.
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
      boards[index++] =  i.next();
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
    try (
        // 1. 바이너리 데이터(바이트배열)를 출력할 도구를 준비한다 .
        FileOutputStream out = new FileOutputStream(filename)){

      // 2. 게시글 개수를 저장 4바이트
      out.write(BinaryEncoder.write(list.size()));


      //3 게시글 출력
      //목록에서board객체를 꺼내 바이트 배열로 만든후 출력
      for (Board b : list) {
        out.write(BinaryEncoder.write(b.getNo()));       // 0x348a3b10 = 0x348a3b10
        out.write(BinaryEncoder.write(b.getTitle()));
        out.write(BinaryEncoder.write(b.getContent()));
        out.write(BinaryEncoder.write(b.getPassword()));
        out.write(BinaryEncoder.write(b.getViewCount()));
        out.write(BinaryEncoder.write(b.getCreatedDate()));
      }

    }catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void load(String filename) {
    if (list.size() > 0) { // 중복로딩 방지
      return;
    }
    try (
        FileInputStream in = new FileInputStream(filename)) {

      int size = BinaryDecoder.readInt(in); //ab------

      for (int i = 0; i < size; i++) {
        // 바이너리 데이터를 저장한 순서대로 읽어서 Board 객체에 담는다
        Board b = new Board();
        b.setNo(BinaryDecoder.readInt(in));
        b.setTitle(BinaryDecoder.readString(in));
        b.setContent(BinaryDecoder.readString(in));
        b.setPassword(BinaryDecoder.readString(in));
        b.setViewCount(BinaryDecoder.readInt(in));
        b.setCreatedDate(BinaryDecoder.readString(in));

        // Board 객체를 목록에 추가
        list.add(b);
      }
      if (list.size() > 0 ) {
        lastNo = list.get(list.size() - 1).getNo();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}























