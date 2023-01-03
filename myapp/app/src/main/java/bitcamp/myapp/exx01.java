package bitcamp.myapp;

public class  exx01 {
  static void charAt1() {
    String text = "가나다라마";
  }
  public static void main(String[] args) {
    String a = "AAdfg";
    System.out.println(a.length());
    //    문자 개수 세기
    for (int i = 0; i < a.length(); i++) {
      char temp = a.charAt(i);
      System.out.println(temp);
      if (a.charAt(i) == a.charAt(i+1)) {
          
      }

    }
  }
}

