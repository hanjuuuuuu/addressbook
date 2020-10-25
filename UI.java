package address;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/* ���α׷� �̸� : UI.java
���α׷� ���� : Person, AddressBook ��ü�� �̿��� �ּҷ��� �����ϴ� ���α׷�
�ۼ��� : 2020-09-08
�ۼ��� :  ������*/

public class UI {
	public static void main(String[] args) throws Exception {
		
		ObjectInputStream in=null;
		ObjectOutputStream out=null;
		Scanner scan = new Scanner(System.in);
		
		Person p;
		AddressBook ad = null;
		int menu=0;	//�޴� ���� �� �ʿ��� ����
		String name, phoneNum, address, email;
		
		
		//���� read
		try {
			 in = new ObjectInputStream(new FileInputStream("AddressBook.dat"));
	         ad = new AddressBook(100, in);
	         in.close(); //in �ݱ�
		}catch (FileNotFoundException fnfe) {
			System.out.println("File not Found... First Time Start......");
		    ad = new AddressBook(100);
		}catch (Exception ex) {
			System.out.println("���� ���� �˼� ���� ������ �߻��߽��ϴ�.");
		}


		loop:
		while(true)
		{
			
				System.out.println("----------------------------<  �ּҷ� ���α׷�    >--------------------------------------------");
				System.out.println("  1.�ּҷ� ���     2.�ּҷ� �˻�      3.�ּҷ� ����     4.�ּҷ� ����     5.��ü �ּҷ� ��ȸ     6.�ּҷ� ����     7.���α׷� ����");
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.print("���Ͻô� �޴��� ��ȣ�� �Է��ϼ���. : ");
				
				//�޴� ����-��ȣ �Է� ����
				try{
					menu = scan.nextInt();	
					if(menu<0 || 7<menu) {		
						System.out.println("1���� 7������ ���ڸ� �Է��ϼ���.");
					}
				}catch(InputMismatchException e) {
						System.out.println("���ڸ� �Է����ּ���.");
						scan.nextLine();
				}
				
				switch(menu) 
				{	//---------------------ȸ����ȣ ���------------------------------------------------
				case 1:
					System.out.println("============[ �ּҷ� ���  ]============");
					scan.nextLine();
					do { //(do-while)�̸� ��ġ�� �ʰ� �Է��ϰ� �ϴ� �ڵ�
						System.out.print("�̸��� �Է��ϼ���. : ");
						name = scan.nextLine();
						if(ad.checkName(name)==true) {
							System.out.println("�̹� ��ϵ� �̸��Դϴ�. �̸� �ڿ� ���ڸ� �ٿ� �����ϼ���. (ex:������1, ������2 ,������3)");
						}
						else break;
						}while(true);
					
					System.out.print("��ȭ��ȣ�� �Է��ϼ���. : ");
					phoneNum = scan.nextLine();
					if(ad.checkPhoneNum(phoneNum)==true) { //���� ��ϵ� ��ȭ��ȣ�� ��� �޴��� ���ư��� ��
						System.out.println("�̹� ��ϵ� ��ȭ��ȣ �Դϴ�.");
						break;
					}
					System.out.print("�ּҸ� �Է��ϼ���. : ");
					address = scan.nextLine();
					System.out.print("�̸����� �Է��ϼ���. : ");
					email = scan.nextLine();	
	
					try {
						ad.add(new Person(name,phoneNum,address,email));	//�ּҷ� ��� �޼ҵ�
					} catch (Exception e) {
						System.out.println("��� ���� �ο� ���� �ʰ��Ǿ����ϴ�.");			
						break;
					}
					
					System.out.println("\n�ּҷ��� ���������� �ԷµǾ����ϴ�.\n");
					
					break;
					
				//-------------------ȸ����ȣ �˻�------------------------------------------------------
				case 2: 	
					System.out.println("============[ �ּҷ� �˻�  ]============");
					System.out.print("1.�̸����� �˻�   2.��ȭ��ȣ�� �˻� : ");
					do { //(do-while)�޴� ��ȣ ����� �Է��ϰ� �ϴ� �ڵ�
						int num=0;
						
						try{
							num = scan.nextInt();	//�޴� ��ȣ �Է� ����
						}catch(InputMismatchException e) {
								System.out.print("���ڸ� �Է��ؾ��մϴ�. ");
								scan.nextLine();//
						}
							
						
						if(num==1) {//�̸����� �˻�-----------------------------------------------------
							System.out.print("�˻��Ͻ� �̸��� �Է��ϼ���. : ");
							scan.nextLine();
							name=scan.nextLine(); // �̸� �Է� ����
	
							try {								
									int index=ad.searchName(name);
									System.out.println("< �ּҷ� ��ȣ : "+index+" >");
									System.out.println("�̸� : " + ad.getPerson(index).getName());
									System.out.println("��ȭ��ȣ : " + ad.getPerson(index).getPhoneNum());
									System.out.println("���ּ� : " + ad.getPerson(index).getAddress());
									System.out.println("�̸��� : " + ad.getPerson(index).getEmail());
							} catch (Exception e) {
									System.out.println("��ϵ� �̸��� �����ϴ�.");			
							}
							break;
						}
						else if(num==2) {//��ȭ��ȣ�� �˻�-----------------------------------------------
							
							System.out.print("�˻��Ͻ� ��ȭ��ȣ�� �Է��ϼ���. : ");
							scan.nextLine();
							phoneNum=scan.nextLine();// ��ȭ��ȣ �Է� ����
							
							try {							
									int index=ad.searchPhoneNum(phoneNum);
									System.out.println("< �ּҷ� ��ȣ : "+index+" >");
									System.out.println("�̸� : " + ad.getPerson(index).getName());
									System.out.println("��ȭ��ȣ : " + ad.getPerson(index).getPhoneNum());
									System.out.println("���ּ� : " + ad.getPerson(index).getAddress());
									System.out.println("�̸��� : " + ad.getPerson(index).getEmail());
							} catch (Exception e) {
								System.out.println("��ϵ� ��ȭ��ȣ�� �����ϴ�.");			
							}						
							break;
						}
						else {
							System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��ϼ���.");
							System.out.print("1.�̸����� �˻�   2.��ȭ��ȣ�� �˻� : ");
						}
						}while(true);
					
					System.out.println("\n>>>");
					System.out.println("\n�ּҷ� �˻��� �����մϴ�.\n");
					
					break;
					
				case 3: 	//�ּҷ� ����---------------------------------------------------------------
					System.out.println("============[ �ּҷ� ����  ]============");
					System.out.print("������ �ּҷ��� ȸ�� �̸��� �Է��ϼ��� : ");
					scan.nextLine();

					try {					
							name=scan.nextLine();
							int index=ad.searchName(name); 
							
							do { //(do-while)�̸� ��ġ�� �ʰ� �����ϴ� �ڵ�
								System.out.print("�̸��� �Է��ϼ���. : ");
								name = scan.nextLine();
								if(name.equals(ad.getPerson(index).getName())) {//�̸� ���� ���ϴ°��
									break;
								}
								else {
									if(ad.checkName(name)==true) {//�̸� ������ ��, ��ϵ� �̸��� ���
									System.out.println("�̹� ��ϵ� �̸��Դϴ�. �̸� �ڿ� ���ڸ� �ٿ� �����ϼ���. (ex:�����1, �����2 ,�����3)");
									}
									else break;
								}
							}while(true);
					
							do { //(do-while)��ȭ��ȣ ��ġ�� �ʰ� �����ϴ� �ڵ�
								System.out.print("��ȭ��ȣ�� �Է��ϼ���. : ");
								phoneNum = scan.nextLine();
								if(phoneNum.equals(ad.getPerson(index).getPhoneNum())) {//��ȭ��ȣ ���� ���ϴ°��
									break;
								}
								else {
									if(ad.checkPhoneNum(phoneNum)==true) { //��ȭ��ȣ ������ ��, ��ϵ� ��ȭ��ȣ�� ��� 
										System.out.println("�̹� ��ϵ� ��ȭ��ȣ �Դϴ�.");
									}
									else break;
								}	
							}while(true);
							
							System.out.print("�ּҸ� �Է��ϼ���. : ");
							address = scan.nextLine();
							System.out.print("�̸����� �Է��ϼ���. : ");
							email = scan.nextLine();	
			
							p=new Person(name,phoneNum,address,email);	
											
							ad.modify(index,p); //�ּҷ� ����
							System.out.println("������ �Ϸ�Ǿ����ϴ�.");
							
					}catch (Exception e) {
							System.out.println("��ϵ� �ּҷ��� �����ϴ�.");			
					}
					break;
					
				case 4: // �ּҷ� ����------------------------------------------------------------------
					System.out.println("============[ �ּҷ� ����  ]============");
					
					if(ad.getCount() == 0) {
						System.out.println("��ϵ� �ּҷ��� �����ϴ�.");
						break;
					}
					
					System.out.print("������ �ּҷ��� ȸ�� �̸��� �Է��ϼ��� : ");
					scan.nextLine();

					try {					
							name=scan.nextLine();
							int index=ad.searchName(name); 
							ad.delete(index);
							System.out.println("�ش� �ּҷ��� �����Ǿ����ϴ�.");
							
					}catch (Exception e) {
							System.out.println("��ϵ� �ּҷ��� �����ϴ�.");			
					}
					break;
					
				case 5: // ��ü �ּҷ� ��ȸ --------------------------------------------------------------
					System.out.println("============[ ��ü �ּҷ� ��ȸ  ]============");
					int c=ad.getCount();
					if(c==0) {
						System.out.println("��ϵ� �ּҷ��� �����ϴ�.");
					}
					else {
						for(int i=0; i<c; i++) {
							System.out.println("< �ּҷ� ��ȣ : "+i+" >");
							System.out.println("�̸� : " + ad.getPerson(i).getName());
							System.out.println("��ȭ��ȣ : " + ad.getPerson(i).getPhoneNum());
							System.out.println("���ּ� : " + ad.getPerson(i).getAddress());
							System.out.println("�̸��� : " + ad.getPerson(i).getEmail());
						}
					}
					break;
				
				case 6: // �ּҷ� ����------------------------------------------------------------------
					try {
						out = new ObjectOutputStream(new FileOutputStream("Addressbook.dat"));
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
					try {
						ad.writeFile(out);
						System.out.println("����Ǿ����ϴ�.");
					} catch (FileNotFoundException fnfe) {
						System.out.println(fnfe.getMessage());
					} catch(IOException ioe) {
						System.out.println("������ �����߽��ϴ�.");
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
					
					finally {
						//close()�̿��� out�� ����� �������� ����
						try {
							out.close();
						} catch (IOException ex) {
							System.out.println(ex.getMessage());
						} catch(Exception ex) {
							System.out.println(ex.getMessage());
						}
					}
					break;
					
				case 7:  // ���α׷� ����----------------------------------------------------------------
					System.out.println("============[ ���α׷��� �����մϴ�. ]============");
					scan.close(); 
					break loop;
					
				default:
					break;
					
			}//switch	
		}//while	
	}//main()
}//class UI


