package kdata.manager;

import java.util.Scanner;

public class StudentManagerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//1. �л� �����߰�(�Ѹ��� �����ϴ� Ŭ���� �����(��ȣ�� �̸�))
		//toString, hashCode�� ����
		//2. �л�����Ʈ(������ �����ϴ� Ŭ����, �л����� Ŭ����(StudentManager), ����Ʈ�� �ϳ��� ���������(new student, new manager �� �������ص� �ѹ� -> static(Ŭ������ �ϳ�))
		//�л������߰� �޼ҵ� add()
		//�л����� ��� print()(iterator �Ǵ� for���̿�)
		//3. main managerTest
		
		//1��Ŭ���ϸ� add()
		//2��Ŭ���ϸ� print() �л�����Ʈ �̸����� �������� ������ ���
		//3��Ŭ���ϸ� ����

		Scanner sc = new Scanner(System.in);
		
		int num = 0;
		String name = null;
		String tel = null;
		String email = null;
		int input = 0;
		
		StudentManager sm = new StudentManager();
		
		while(input != 8){	
			System.out.println("> 1.add 2.print 3.search(�й�) 4.search(�̸�) 5.remove(�й�) 6.remove(�̸�) 7.exit");
			input = sc.nextInt();
			
			if(input == 1){
				System.out.println("> �й��� �Է��ϼ���: ");
				num = sc.nextInt();
				sc.nextLine();
				System.out.println("> �̸��� �Է��ϼ���: ");
				name = sc.nextLine();
				System.out.println("> ��ȭ��ȣ�� �Է��ϼ���: ");
				tel = sc.nextLine();
				System.out.println("> �̸����� �Է��ϼ���: ");
				email = sc.nextLine();
				
				Student student = new Student(num, name, tel, email);
				sm.add(student);
				System.out.println("�л������� ���ԵǾ����ϴ�.");
			} 
			else if(input == 2){
				sm.print();
			}
			else if(input == 3){
				System.out.println("> �˻��� �й��� �Է��ϼ���: ");
				num = sc.nextInt();
				if(sm.seach(num) != null){
					System.out.println(sm.seach(num));
				}
				else
					System.out.println(num + "�� �л��� �����ϴ�.");
			}
			else if(input == 4){
				System.out.println("> �˻��� �̸��� �Է��ϼ���: ");
				sc.nextLine();
				name = sc.nextLine();
				if(sm.seach(name) != null){
					System.out.println(sm.seach(name));
				}
				else
					System.out.println(name + " �л��� �����ϴ�.");
			}
			else if(input == 5){
				System.out.println("> ������ �й��� �Է��ϼ���: ");
				num = sc.nextInt();
				if(sm.remove(num) != false){
					System.out.println(num + "�� �л������� �����Ͽ����ϴ�.");
				}
				else
					System.out.println(num + "�� �л��� �����ϴ�.");
			}
			else if(input == 6){
				System.out.println("> ������ �̸��� �Է��ϼ���: ");
				sc.nextLine();
				name = sc.nextLine();
				if(sm.remove(name) != false){
					System.out.println(name + " �л� ������ �����Ͽ����ϴ�.");
				}
				else
					System.out.println(name + " �л��� �����ϴ�.");
				System.out.println(sm.remove(name));
			}
			
		}
		System.out.println("> ����Ǿ����ϴ�.");
		System.exit(input);
	}
	
	//-> Ŭ���� �ε�(���� ���� ����ϰ��� �ϴ� Ŭ������ ���κҷ����� ��) -> main�޼ҵ� ã�ƴڤ�

}
