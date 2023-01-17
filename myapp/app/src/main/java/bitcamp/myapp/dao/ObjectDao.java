package bitcamp.myapp.dao;

import java.util.Arrays;

public abstract class ObjectDao {
  private static final int SIZE = 100;

  private int count;
  protected Object[] objects = new Object[SIZE];

  public void insert(Object object) {
    this.objects[this.count++] = object;
  }

  public Object[] findAll() {
    // 배열의 값 복제
    //    Object[] arr = new Object[this.count];
    //    for (int i = 0; i < this.count; i++) {
    //      arr[i] = this.objects[i];
    //    }
    //    return arr;

    // 위와 같다!
    return Arrays.copyOf(objects, count);
  }

  public void update(Object object) {
    this.objects[this.indexOf(object)] = object;
  }

  public void delete(Object object) {
    for (int i = this.indexOf(object) + 1; i < this.count; i++) {
      this.objects[i - 1] = this.objects[i];
    }
    this.objects[--this.count] = null; // 레퍼런스 카운트를 줄인다.
  }

  // 객체의 위치를 찾는 것은
  // 객체의 타입에 따라 다를수 잇기 때문에
  // 이 클래스에서 정의하지 말고,
  // 서브클래스에서 정의 하도록
  // 책임을 위임해야한다
  protected abstract int indexOf(Object b);
  // 자식들에게 직접접근시키지 말고 공개용 메소드에 변수를 담아서 공유
  public int size() {
    return this.count;
  }

  // 개발하는 중에 서브 클래스들이 공통으로 필요로 하는 기능을 발견하게 된다
  // 크런 상황이면 super클래스에 메서드를 정의하면 된다
  public Object get(int i) {
    if (i < 0 || i >= this.count) {
      return null;
    }
    return objects[i];
  }


}







