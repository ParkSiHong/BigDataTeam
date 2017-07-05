package kdata.manager;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class StudentManagerTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// 1. 학생 정보 추가
		// 2. 학생 리스트 이름으로 오름차순 정렬 후 출력
		// 학생 정보를 원래 만들었던 것처럼 똑같이 만듬

		// 1번 누르면 add 메세지 출력
		// 2번 누르면 한명 리스트 나오게
		// 3번 검색
		// 4번 삭제
		// 5번 전체 총점
		// 6번 최고 점수
		// 7번 최저  점수
		// 8번 종료
		int num;
		String name = null;
		StudentManager s = new StudentManager();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1. add 2.list 3.search 4.remove 5.total 6.exit : ");

			int select = sc.nextInt();// 1, \r, \n

			switch (select) {
			case 1:
				s.add();
				break;
			case 2:
				//String printName = sc.next();
				s.print(name);
				break;
			case 3:
				System.out.println("출력할 이름을 입력하세요: ");
				String searchName = sc.next();
				s.search(searchName);
				break;
			case 4:
				System.out.println("삭제할 이름을 입력하세요: ");
				String removeName = sc.next();
				s.remove(removeName);
				break;
			case 5:
				System.out.print("전체 총합은: ");
				s.total();
				break;
			case 6:
				s.exit();
				break;
			}
		}
	}
}
