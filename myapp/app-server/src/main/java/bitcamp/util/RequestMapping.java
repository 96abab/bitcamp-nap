package bitcamp.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// 애노테이션 유지 정책 변경하기
// => class : 컴파일 했을때 바이트 코드에 유지하기
// RUNTIME : class + 실행중 사용가능

@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
  String value(); // 필수 속성
}
