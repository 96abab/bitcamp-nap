// # 인스턴스 변수 의 종류
//
package com.eomcs.oop.ex03;

public class Exam0100 {

  // static 필드 = 클래스 필드(변수)
  //- 클래스를 로딩할 때 Method Area영역에 생성된다
  //- 클래스는 단 한번만 로딩된다
  // 따라서 스태틱 변수도 한번만 생성된다
  // JVM을 종료하면 한번에 제거
  static int a;

  // non-static 필드 = 인스터스 필드
  // new 연산자를 실행할 때 Heap 영역에 생성된다
  // new 연산자를 실행할 때마다 생성
  // Garbage Collector에 의해 인스턴스가 해제될 때 제거
  int b;


  public static void main(String[] args /* 파라미터 = 로컬 변수*/) {


    // 로컬변수
    // 메서드가 호출될 때 JVM Stack 영역에 생성된다
    // 메서드 호풀이 끝나면 제거
    int c;
    c = 100;

    // <==== 현재 실행 시점
    // Method Area: a 변수 존재
    // JVM Stack : args, c 변수 존재
    // Heap : 아직 생성객체 없음

    Exam0100 obj; // main() 호출 할 때 초반에 시작 시점에 JVM Stack에 생성된 상태

    obj = new Exam0100();

    // <==== 현재 실행 시점
    // Method Area: a 변수 존재
    // JVM Stack : args, c , obj 변수 존재
    // Heap : b 변수존재

    System.out.println(c);

  }
}



