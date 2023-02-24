package bitcamp.myapp.service;

import java.util.List;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.BoardFile;

public interface BoardService {


  public void add(Board board) ;

  public List<Board> list(String keyword) ;

  public Board get(int no) ;


  public void update(Board board) ;

  public void delete(int no) ;

  public void deleteFile(int fileNo) ;

  public BoardFile getfile(int fileNo);
}





