package bitcamp.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class BinaryDecoder {
  public static int readInt(InputStream in) throws Exception {
    int value = 0;
    value = in.read() << 24; // a3 => a300 0000 // 0x12345678 -> 0x78000000
    value |= in.read()<< 16; // a3 => 00a3 0000 // 0x12345678 -> 0x56780000
    value |= in.read() << 8; // 0x12345678 -> 0x34567800
    value |= in.read(); // 0x12345678 -> 0x12345678
    return value;
  }

  public static boolean readBoolean(InputStream in) throws Exception {
    // [2byte: 문자열의 바이트 배열길이[n바이트 : 문자열의 바이트배열]

    //    boolean blength = in.markSupported();
    boolean bvalue = in.markSupported();


    return bvalue;
  }
  public static char readChar(InputStream in) throws Exception {

    int cha = in.read() << 8;
    cha |= in.read();

    byte chas = (byte) cha;
    char chaa = (char) chas;

    return chaa;

  }
  public static byte readByte(InputStream in) throws Exception {

	    byte by = (byte) in.read();


	    return by;

	  }
  public static String readString(InputStream in) throws Exception {
	    // [2byte: 문자열의 바이트 배열 길이][n바이트: 문자열의 바이트 배열]

	    // 1) 2바이트를 읽어 문자열의 배열 개수를 알아낸다.
	    int length = in.read() << 8;
	    length |= in.read();

	    // 2) 문자열의 배열을 읽어 들일 빈 배열을 준비한다.
	    byte[] bytes = new byte[length];

	    // 3) 문자열의 배열을 읽어 빈 배열에 담는다.
	    in.read(bytes, 0, length);

	    // 4) 배열에 들어 있는 문자 코드를 가지고 String 객체를 생성한다.
	    String str = new String(bytes);

	    return str;
	  }
  public static void main(String[] args) throws Exception {
    byte[] bytes = new byte[] {0x00, 0x05, (byte)0x41 , (byte)0x42, (byte)0xea, (byte)0xb0, (byte)0x80};

    ByteArrayInputStream in = new ByteArrayInputStream(bytes);
    // 바이트 배열을 읽어서 String 을 리턴
    String str = BinaryDecoder.readString(in);
    // 나오는지 툴력
    System.out.println(str);

    in.close();
  }
}

