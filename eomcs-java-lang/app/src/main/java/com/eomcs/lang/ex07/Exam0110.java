package com.eomcs.lang.ex07;

import java.util.Scanner;

//# 메서드 : 사용 전
//
public class Exam0110 {
	static void printSpace(int len) {
		for (int i = 1; i <= len; i++) {
			System.out.print(" ");
		}
	}
	static void printStar(int len) {
		for (int i = 1; i <= len; i++) {
			System.out.print("*");
		}
	}
    static int getSpaceLength(int totalStar, int displayStar) {
    	return (totalStar - displayStar) / 2;
    }
  public static void main(String[] args) {
    Scanner keyScan = new Scanner(System.in);
    System.out.print("밑변의 길이? ");
    int len = keyScan.nextInt();
    keyScan.close();

    for (int starLen = 1; starLen <= len; starLen += 2) {
    	printSpace(getSpaceLength(len, starLen));
    	printStar(starLen);
      System.out.println();
    }
  }
}