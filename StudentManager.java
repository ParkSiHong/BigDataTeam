package kdata.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//2. �л�����Ʈ(������ �����ϴ� Ŭ����, �л����� Ŭ����(StudentManager), ����Ʈ�� �ϳ��� ���������
//(new student, new manager �� �������ص� �ѹ� -> static(Ŭ������ �ϳ�))
//�л������߰� �޼ҵ� add()
//�л����� ��� print()(iterator �Ǵ� for���̿�)
public class StudentManager {
	
	private static List<Student> list;
	
	public StudentManager(){
		if(list == null) //list�� �ѹ��� ����������� static����
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
