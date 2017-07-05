package kdata.manager;

import java.util.Scanner;

public class StudentManagerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//1. 학생 정보추가(한명꺼만 저장하는 클래스 만들기(번호랑 이름))
		//toString, hashCode를 자율
		//2. 학생리스트(여러명 저장하는 클래스, 학생관리 클래스(StudentManager), 리스트는 하나만 만들어지게(new student, new manager 등 여러번해도 한번 -> static(클래스당 하나))
		//학생정보추가 메소드 add()
		//학생정보 출력 print()(iterator 또는 for문이용)
		//3. main managerTest
		
		//1번클릭하면 add()
		//2번클릭하면 print() 학생리스트 이름으로 오름차순 정렬후 출력
		//3번클릭하면 종료

		Scanner sc = new Scanner(System.in);
		
		int num = 0;
		String name = null;
		String tel = null;
		String email = null;
		int input = 0;
		
		StudentManager sm = new StudentManager();
		
		while(input != 8){	
			System.out.println("> 1.add 2.print 3.search(학번) 4.search(이릅) 5.remove(학번) 6.remove(이름) 7.exit");
			input = sc.nextInt();
			
			if(input == 1){
				System.out.println("> 학번을 입력하세요: ");
				num = sc.nextInt();
				sc.nextLine();
				System.out.println("> 이름을 입력하세요: ");
				name = sc.nextLine();
				System.out.println("> 전화번호을 입력하세요: ");
				tel = sc.nextLine();
				System.out.println("> 이메일을 입력하세요: ");
				email = sc.nextLine();
				
				Student student = new Student(num, name, tel, email);
				sm.add(student);
				System.out.println("학생정보가 삽입되었습니다.");
			} 
			else if(input == 2){
				sm.print();
			}
			else if(input == 3){
				System.out.println("> 검색할 학번을 입력하세요: ");
				num = sc.nextInt();
				if(sm.seach(num) != null){
					System.out.println(sm.seach(num));
				}
				else
					System.out.println(num + "번 학생이 없습니다.");
			}
			else if(input == 4){
				System.out.println("> 검색할 이름을 입력하세요: ");
				sc.nextLine();
				name = sc.nextLine();
				if(sm.seach(name) != null){
					System.out.println(sm.seach(name));
				}
				else
					System.out.println(name + " 학생이 없습니다.");
			}
			else if(input == 5){
				System.out.println("> 삭제할 학번을 입력하세요: ");
				num = sc.nextInt();
				if(sm.remove(num) != false){
					System.out.println(num + "번 학생정보를 삭제하였습니다.");
				}
				else
					System.out.println(num + "번 학생이 없습니다.");
			}
			else if(input == 6){
				System.out.println("> 삭제할 이름을 입력하세요: ");
				sc.nextLine();
				name = sc.nextLine();
				if(sm.remove(name) != false){
					System.out.println(name + " 학생 정보를 삭제하였습니다.");
				}
				else
					System.out.println(name + " 학생이 없습니다.");
				System.out.println(sm.remove(name));
			}
			
		}
		System.out.println("> 종료되었습니다.");
		System.exit(input);
	}
	
	//-> 클래스 로드(내가 지금 사용하고자 하는 클래스를 전부불러오는 곳) -> main메소드 찾아닥ㅁ

}
