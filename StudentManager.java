package kdata.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//2. 학생리스트(여러명 저장하는 클래스, 학생관리 클래스(StudentManager), 리스트는 하나만 만들어지게
//(new student, new manager 등 여러번해도 한번 -> static(클래스당 하나))
//학생정보추가 메소드 add()
//학생정보 출력 print()(iterator 또는 for문이용)
public class StudentManager {
	
	private static List<Student> list;
	
	public StudentManager(){
		if(list == null) //list가 한번만 만들어지도록 static포함
			list = new ArrayList<>();
	}
	
	public void add(Student student){
		list.add(student);
	}
	
	public void print(){
		Iterator it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
//		for(Student st: list){
//			System.out.println(st.getNum() + ", "+ st.getName());
//		}
		
	}

	public boolean remove(int num) {
		// TODO Auto-generated method stub
		boolean result = false;
		for(Student student: list){
			if(student.getNum() == num){
				result = list.remove(student);
				break;
			}	
		}
		return result;
	}
	
	public boolean remove(String name) {
		// TODO Auto-generated method stub
		boolean result = false;

		for(Student student: list){
			if(student.getName().equals(name)){
				result = list.remove(student);
				break;
			}
		}
		return result;			
	}

	public Student seach(int num) {
		// TODO Auto-generated method stub
		Student result = null;
		for(Student student: list){
			if(student.getNum() == num){
				result = student;
			}
		}
		return result;
	}
	
	public Student seach(String name) {
		// TODO Auto-generated method stub
		Student result = null;
		for(Student student: list){
			if(student.getName().equals(name)){
				result = student;
			}
		}
		return result;
	}
	
	

}
