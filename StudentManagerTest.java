package kdata.manager;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class StudentManagerTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// 1. �л� ���� �߰�
		// 2. �л� ����Ʈ �̸����� �������� ���� �� ���
		// �л� ������ ���� ������� ��ó�� �Ȱ��� ����

		// 1�� ������ add �޼��� ���
		// 2�� ������ �Ѹ� ����Ʈ ������
		// 3�� �˻�
		// 4�� ����
		// 5�� ��ü ����
		// 6�� �ְ� ����
		// 7�� ����  ����
		// 8�� ����
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
				System.out.println("����� �̸��� �Է��ϼ���: ");
				String searchName = sc.next();
				s.search(searchName);
				break;
			case 4:
				System.out.println("������ �̸��� �Է��ϼ���: ");
				String removeName = sc.next();
				s.remove(removeName);
				break;
			case 5:
				System.out.print("��ü ������: ");
				s.total();
				break;
			case 6:
				s.exit();
				break;
			}
		}
	}
}
