package bitcamp.bootapp.vo;

// 회원 데이터를 담을 메모리를 설계한다.
public class Teacher {
  private int no;
  private String name;
  private String tel;
  private String eMail;
  private String eduCation;
  private String uniVerSity;
  private String marJoIn;
  private int pay;
  private String createdDate;


  public String geteMail() {
    return eMail;
  }
  public void seteMail(String eMail) {
    this.eMail = eMail;
  }
  public String getEduCation() {
    return eduCation;
  }
  public void setEduCation(String eduCation) {
    this.eduCation = eduCation;
  }
  public String getUniVerSity() {
    return uniVerSity;
  }
  public void setUniVerSity(String uniVerSity) {
    this.uniVerSity = uniVerSity;
  }
  public String getMarJoIn() {
    return marJoIn;
  }
  public void setMarJoIn(String marJoIn) {
    this.marJoIn = marJoIn;
  }
  public int getPay() {
    return pay;
  }
  public void setPay(int pay) {
    this.pay = pay;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }


}
