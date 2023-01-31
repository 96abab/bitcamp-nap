package bitcamp.myapp.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Student;

public class StudentDao {

  List<Student> list;

  int lastNo;

  public StudentDao(List<Student> list) {
    this.list = list;
  }

  public void insert(Student s) {
    s.setNo(++lastNo);
    s.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    list.add(s);
  }

  public Student[] findAll() {
    Student[] students = new Student[list.size()];
    Iterator<Student> i = list.iterator();
    int index = 0;
    while (i.hasNext()) {
      students[index++] = i.next();
    }
    return students;
  }

  public Student findByNo(int no) {
    Student s = new Student();
    s.setNo(no);

    int index = list.indexOf(s);
    if (index == -1) {
      return null;
    }
    return list.get(index);
  }

  public void update(Student s) {
    int index = list.indexOf(s);
    list.set(index, s);
  }

  public boolean delete(Student s) {
    return list.remove(s);
  }

  public void save(String filename) {
	    try (FileWriter out = new FileWriter(filename)) {
	    	

            for (Student s : list) {
              out.write(String.format("%d,%s,%s,%S,%s,%s,%b,%s,%s\n",
            		  s.getNo(),
            		  s.getTel(),
            		  s.getPostNo(),
            		  s.getBasicAddress(),
            		  s.getDetailAddress(),
            		  s.isWorking(),
            		  s.getGender(),
            		  s.getLevel()
            		  ));
            }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  public void load(String filename) {
    if (list.size() > 0) {
      return;
    }

    try (Scanner in = new Scanner(new FileReader(filename))) {

    	while (true) {
    		try {
    		String[] values = in.nextLine().split(",");
    		Student s = new Student();
    		s.setNo(Integer.parseInt(values[0]));
    		s.setTel(values[1]);
    		s.setPostNo(values[2]);
    		s.setBasicAddress(values[3]);
    		s.setDetailAddress(values[4]);
    		s.setWorking(values[5] == true ? 1 : 0);
    		list.add(s);
    		}catch(Exception e) {
    			break;
    		}
    	}
    } catch (FileNotFoundException e) {
      System.out.println("데이터 파일이 존재하지 않습니다!");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}







