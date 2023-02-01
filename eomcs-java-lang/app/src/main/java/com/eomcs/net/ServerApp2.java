package com.eomcs.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp2 {

  public static void main(String[] args) throws Exception{



    System.out.println("서버 실행");

    ServerSocket serverSocket = new ServerSocket(8888);
    Socket socket = serverSocket.accept();

    System.out.println("클라이언트와 연결");

    DataInputStream in = new DataInputStream(socket.getInputStream());
    DataOutputStream out = new DataOutputStream(socket.getOutputStream());

    // 파일이름 읽기
    String filename = in.readUTF();

    // 클라이언트가 보낸 파일의 크기를 익ㄹ는다
    long length = in.readLong();

    // 클라이언트가 보낸 사진 바이트를 파일로 출력
    FileOutputStream fileOut = new FileOutputStream(filename);

    for (long i = 0; i < length; i++) {
      fileOut.write(in.read());
    }
    fileOut.close();
    out.writeUTF("완료");

    in.close();
    socket.close();
    serverSocket.close();

    System.out.println("서버종료");
  }

}
