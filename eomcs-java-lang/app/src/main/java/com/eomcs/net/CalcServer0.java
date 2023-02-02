package com.eomcs.net;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CalcServer0 {

  public static void main(String[] args) throws Exception{


    System.out.println("서버 실행");

    ServerSocket serverSocket = new ServerSocket(8888);
    Socket socket = serverSocket.accept();

    System.out.println("클라이언트와 연결");

    Scanner in = new Scanner(socket.getInputStream());
    PrintStream out = new PrintStream(socket.getOutputStream());

    while (true) {
      // 클라이언트가 보낸 문자열 한 줄을 읽을때 까지 리턴하지 않는다
      String message = in.nextLine();
      System.out.println(message);

      System.out.println("첫번째 숫자를 입력하세요");
      String str1 = keyScan.nextLine();
      System.out.println("사칙연산 기호중 하나를 입력하세요");
      String op = keyScan.nextLine();
      System.out.println("두번째 숫자를 입력하세요");
      String str2 = keyScan.nextLine();
      int num1 = Integer.parseInt(str1);
      int num2 = Integer.parseInt(str2);
      int num3;
      if (op.equals("+")) {
        num3 = num1 + num2;
      } else if (op.equals("-")) {
        num3 = num1 - num2;
      } else if (op.equals("/")) {
        num3 = num1 / num2;
      } else {
        num3 = num1 * num2;
      }
      if (message.equals("quit")) {
        break;
      }
      System.out.println(str1 + op + str2 + "=" + num3);

      if (message.equals("quit")) {
        break;
      }

      System.out.println("입력 > ");
      //      String str = .nextLine();
      out.println(str);

    }

    in.close();
    socket.close();
    serverSocket.close();

    System.out.println("서버종료");
  }


}
