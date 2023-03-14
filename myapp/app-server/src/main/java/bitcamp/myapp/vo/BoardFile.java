package bitcamp.myapp.vo;

import java.io.Serializable;
import lombok.Data;

@Data
public class BoardFile implements Serializable {
  private static final long serialVersionUID = 1L;

  private int no;
  private String filepath;
  private String originalFilename;
  private String mimeType;
  private int boardNo;
}
