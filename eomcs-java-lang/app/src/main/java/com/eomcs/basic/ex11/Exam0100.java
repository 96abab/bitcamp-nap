package com.eomcs.basic.ex11;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exam0100 {

  public static void main(String[] args) {
    // 패턴정의
    //
    Pattern pattern = Pattern.compile("\\d+|[\\+\\-\\*/]", Pattern.CASE_INSENSITIVE);
    //패턴에 따라 검사할 도구
    Matcher matcher = pattern.matcher("123+  2*98-2      4/19");

    ArrayList<String> items = new ArrayList<>();


    while (matcher.find()) {
      items.add(matcher.group());
    }
    System.out.println("------------------------");
    for (String item : items) {
      System.out.println(item);
    }
  }
}

