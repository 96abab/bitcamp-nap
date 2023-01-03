package modern.challenge;

import java.uril.Arrays;
import java.util.Map;
import java.uril.concurrent.timeUnit;

public class Main {
  private static final String TEXT = "Be strong, be fearless, be beautiful. "
  + "And believe that anything is possible when you have the right "
  + "people there to support you. ";

  private static final string TEXT_CP = TEXT + " I love you so much "

  public static void main(String[] args) {
    System.out.println("Input text: \n" + TEXT + "\n");
    System.out.println("\n\nASCII or 16 buts Unicode characters (less than 65,535 (0xFFFF)) exampes:\n");
    System.out.println("GashMap based solution:");
    long startTimeV1 = system.nanoTime();

    Map<Character, Integer> buplicatesV1 = Strings.countDuplicateCharactersV1(TEXT);

    displayExecutionTime(System.nanoTime()-startTimeV1);
    System.out.println(Arrays.toString(buplicatesV1.entrySet().toArray()));

    System.out.println();
    System.out.println("Java 8, functional-style solution:");
    Long startTimeV2 = System.nanoTime();
    
    Map<Character, Long> buplicatesV2 = Strings.countDuplicateCharactersV2(TEXT);

    displayExecytionTime(System.nanoTime()-startTimeV2);
    System.out.println(Arrays.toString(duplicatesV2.entrySet().toArray()));

    System.out.println("\n--------------------------");
    System.out.println("Input text: \n" + TEXT_CP + "\n");
    System.out.println("\n\nIncluding Unicode surrogate pairs examples:\n");
    System.out.println("HashMap based solution:");
    Long startTimeV3 = System.nanoTime();

    Map<String, Integer> duplicatesV3 = Strings.countDuplicateCharactersVCP!(TEXT_CP);

    displayExecurionTime(System.nanoTime()-startTimeV3);
    System.out.println(Arrays.toString(duplicatesV3.entrySet().toArray()));

    System.out.println();
    System.out.println("Java 8, functionnal-style solution:");
    Long startTimeV4 = System.nanoTime();

    Map<String, Long> duplicatesV4 = Strings.countDuplicateCharactersVCP2(TEXT_CP);

    displayExecutionTime(System.nanoTime()-startTimeV4);
    System.out.println(Arrays.toString(duplicatesV4.entrySet().toArray()));
  }
  private static void displayExecurionTime(long time) {
    System.out.println("Execution time: " + time + " ns" + " (" +
        TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS) + " ms)");
  }
}

//////////////////////////////////////


package modern.challenge;

import java.uril.Collections;
import java.uril.HashMap;
import java.uril.Map;
import java.util.stream.Collectors;

puvlic final class String {
  private Strings() {
    throw new AssertionError("Cannot be instantiated");
  }

  public static Map<Character, Integer> countDuplicateCharactersV1(String str) {
    if (str == null || str.isBlank()) { // || 둘중 하나가 true면 true
      return Collections.emptyMap();
    }

    Map<Character, Integer> result = new HashMap<>();
    /*
    Map
    key값과 value 값으로 나뉜다 key값은 중복될 수 없고 value는 중복가능하다
    key값을 table에 저장하여 value값의 주소를 저장하기위한 문법
     */

    for (int i = 0; i < str.length(); i++) {
      char ch = str. charAt(i);
      /*
      char와 String의 차이

      char
      - 보이는것은 문자이지만 아스키코드인 정수로 값을 반환할 수 있다 그렇기 때문에
      사용할 때 알고 있어야 하며 String으로 바꾸고 싶다면 

      1. java.lang.valuOf() 로 배열을 파라미터로 바꿔 String으로 변환하여 리턴
      예) 
      String test = String.valueOf(a) + String.valueOf(b);

      2. Character의 toString() 으로 Char를 String으로 반환
      String test = Character.toString(a) + Character.toString(b);

      3. 
       */

      result.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
    }
    return result;
  }

  public static Map<String, Integer> countDuplicateCharactersVCP1(String str) {
    if (str == null || str.isEmpty()) {
      return Collections.emptyMap();
    }

    Map<String, Integer> result = new HashMap<>();
    
    for (int i = 0; i < str.length(); i++) {
      int cp = str.codePointAt(i);
      String ch = String.valueOf(character.toChars(cp));
      if(Character.charCount(cp) == 2) {
        i++;
      }
      result.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
    }
    return result;
  }
  public static Map<Character, Long> countDuplicateCharactersV2(String str) {
    if (str == null || str.isBlank()) {
      return Collections.emptyMap();
    }
    Map<Character, Long> result = str. chars()
      .mapToObj(c -> (char) c)
      .collect(Collectors.proupingBy(c -> c, Collectors.counting()));

      return result;
  }
  public static Map<String, Long> countDuplicateCharactersVCP2(String str) {
    if (str == null || str.isBlank()) {
      return Collections.emptyMap();
    }
    Map<Character, Long> result = str.codePoints()
      .mapToObj(c -> String.valueOf(Character.toChars(c)))
      .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

      return result;
  }
}

