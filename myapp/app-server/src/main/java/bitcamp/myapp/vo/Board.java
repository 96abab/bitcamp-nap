package bitcamp.myapp.vo;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Data;

@Data
public class Board implements java.io.Serializable {
  private static final long serialVersionUID = 1L;

  private int no;
  private String title;
  private String content;
  private String password;

  // Jackson 라이브러리가 Date 타입 값을 JSON 문자열로 변환할 때 사용할 규칙을 설정한다.
  @JsonFormat(
      shape = Shape.STRING,
      pattern = "yyyy-MM-dd")
  private Date createdDate;

  private int viewCount;
  private int writerNo;
  private String writerName;
  private Member writer;
  private List<BoardFile> attachedFiles;

  @Override
  public int hashCode() {
    return Objects.hash(no);
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Board other = (Board) obj;
    return no == other.no;
  }

}
