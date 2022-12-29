package bitcamp.myapp;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {

    // 키보드에 입력을 받을 준비
    Scanner keyScanner = new Scanner(System.in);

    int no = Integer.parseInt(keyScanner.nextLine());
    System.out.print("이름? ");
    String name = keyScanner.nextLine();
    System.out.print("전화번호 ");
    String tel = keyScanner.nextLine();
    System.out.print("우편번호? ");
    String postNo = keyScanner.nextLine();
    System.out.print("주소? ");
    String basicAddress = keyScanner.nextLine();
    System.out.print("상세주소? ");
    String detailAddress = keyScanner.nextLine();
    System.out.print("재직자?(true/false)");
    boolean working = Boolean.parseBoolean(keyScanner.nextLine());
    System.out.print("성별?(남/여) ");
    char gender = keyScanner.nextLine().charAt(0); // 남자,W(여자)
    System.out.print("0. 비전공자 ");
    System.out.print("1. 준전공자 ");
    System.out.print("2. 전공자 ");
    System.out.print("전공? ");
    byte level = Byte.parseByte(keyScanner.nextLine()); // 0(비전공자), 1(준전공자), 2(전공자)
    System.out.print("가입일? (예: 2022-12-27) ");
    String createdDate = keyScanner.nextLine();

    System.out.printf("번호: %d\n", no);
    System.out.printf("이름: %s\n" , name);
    System.out.printf("전화번호: %s\n" , tel);
    System.out.printf("우편번호: %s\n" , postNo);
    System.out.printf("주소1: %s\n" , basicAddress);
    System.out.printf("상세주소: %s\n" , detailAddress);
    System.out.printf("재직자: %s\n" , working ? "예" : "아니오");
    System.out.printf("성별: %s\n" , gender == 'M' ? "남자" : "여자" );


    String levelTitle;
    switch (level) {
      case 0: levelTitle = "비전공자"; break;
      case 1: levelTitle = "준전공자"; break;
      default : levelTitle = "전공자";
    }

    System.out.printf("전공: %s\n" , levelTitle);
    System.out.printf("가입일: %s\n" , createdDate);

  }
}
