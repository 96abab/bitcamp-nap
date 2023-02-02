package com.eomcs.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
<<<<<<< HEAD
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalcServer {

  public static String calc(String exp) {
    String p2 = (exp);
    Pattern p1 = Pattern.compile("\\d+|[\\+\\-\\*/]", Pattern.CASE_INSENSITIVE);
    Matcher st = p1.matcher(p2);
    //    StringTokenizer st = new StringTokenizer(exp, " ");
    if (st.countTokens() != 3)
      return "error";

    String res = "";
    int op1 = Integer.parseInt(st.nextToken());
    String opcode = st.nextToken();
    int op2 = Integer.parseInt(st.nextToken());
    switch (opcode) {
      case "+":
        res = Integer.toString(op1 + op2);
        break;
      case "-":
        res = Integer.toString(op1 - op2);
        break;
      case "*":
        res = Integer.toString(op1 * op2);
        break;
      case "/":
        res = Integer.toString(op1 / op2);

        break;
      default:
        res = "error";
    }
    return res;

  }

  public static void main(String[] args) {
    BufferedReader in = null;
    BufferedWriter out = null;
    ServerSocket listener = null;
    Socket socket = null;
    Scanner scanner = new Scanner(System.in);

    try {
      listener = new ServerSocket(9999);
      System.out.println("연결 대기 중......");
      socket = listener.accept();
      System.out.println("연결되었습니다.");
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      while (true) {
        String inputMessage = in.readLine();
        if (inputMessage.equalsIgnoreCase("quit")) {
          System.out.println("Client 에서 quit로 연결을 종료하였음");
          break;
        }
        String outputMessage = calc(inputMessage);
        out.write(outputMessage + "\n");
        out.flush();
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        scanner.close();
        socket.close();
        listener.close();
      } catch (IOException e) {
        System.out.println("입력 값 에러 발생!!");
      }
    }
  }
=======
import java.util.StringTokenizer;

public class CalcServer {

	public static String calc(String exp) {
		StringTokenizer st = new StringTokenizer(exp, " ");
		if (st.countTokens() != 3)
			return "error";

		String res = "";
		int op1 = Integer.parseInt(st.nextToken());
		String opcode = st.nextToken();
		int op2 = Integer.parseInt(st.nextToken());
		switch (opcode) {
		case "+":
			res = Integer.toString(op1 + op2);
			break;
		case "-":
			res = Integer.toString(op1 - op2);
			break;
		case "*":
			res = Integer.toString(op1 * op2);
			break;
		case "/":
			res = Integer.toString(op1 / op2);

			break;
		default:
			res = "error";
		}
		return res;

	}

	public static void main(String[] args) {
		BufferedReader in = null;
		BufferedWriter out = null;
		ServerSocket listener = null;
		Socket socket = null;
		Scanner scanner = new Scanner(System.in);

		try {
			listener = new ServerSocket(9999);
			System.out.println("연결 대기 중......");
			socket = listener.accept();
			System.out.println("연결되었습니다.");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			while (true) {
				String inputMessage = in.readLine();
				if (inputMessage.equalsIgnoreCase("quit")) {
					System.out.println("Client 에서 quit로 연결을 종료하였음");
					break;
				}
				String outputMessage = calc(inputMessage);
				out.write(outputMessage + "\n");
				out.flush();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				scanner.close();
				socket.close();
				listener.close();
			} catch (IOException e) {
				System.out.println("입력 값 에러 발생!!");
			}
		}
	}
>>>>>>> e2b43a02fd2d187ef7455ae43387a9ccc38c5c5f
}
