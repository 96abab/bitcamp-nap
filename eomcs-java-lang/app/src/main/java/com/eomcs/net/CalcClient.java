package com.eomcs.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class CalcClient {

<<<<<<< HEAD
  public static void main(String[] args) {
    BufferedReader in = null;
    BufferedWriter out = null;
    Socket socket = null;
    Scanner scanner = new Scanner(System.in);

    try {
      socket = new Socket("192.168.0.9", 9999);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      while (true) {
        System.out.print("계산 값 입력(예: 1 + 2) >> ");
        String outputMessage = scanner.nextLine();
        if (outputMessage.equalsIgnoreCase("quit")) {
          out.write(outputMessage + "\n");
          out.flush();
          break;
        }
        out.write(outputMessage + "\n");
        out.flush();
        String inputMessage = in.readLine();
        System.out.println("계산 값 출력 : " + inputMessage);
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        scanner.close();
        if (socket != null)
          socket.close();
      } catch (IOException e) {
        System.out.println("입력 값 에러 발생!!");
      }
    }
  }
=======
	public static void main(String[] args) {
		BufferedReader in = null;
		BufferedWriter out = null;
		Socket socket = null;
		Scanner scanner = new Scanner(System.in);

		try {
			socket = new Socket("192.168.27.118", 9999);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			while (true) {
				System.out.print("계산 값 입력(예: 1 + 2) >> ");
				String outputMessage = scanner.nextLine();
				if (outputMessage.equalsIgnoreCase("quit")) {
					out.write(outputMessage + "\n");
					out.flush();
					break;
				}
				out.write(outputMessage + "\n");
				out.flush();
				String inputMessage = in.readLine();
				System.out.println("계산 값 출력 : " + inputMessage);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				scanner.close();
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				System.out.println("입력 값 에러 발생!!");
			}
		}
	}
>>>>>>> e2b43a02fd2d187ef7455ae43387a9ccc38c5c5f

}
