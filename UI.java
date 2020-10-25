package address;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/* 프로그램 이름 : UI.java
프로그램 설명 : Person, AddressBook 객체를 이용해 주소록을 관리하는 프로그램
작성일 : 2020-09-08
작성자 :  김한주*/

public class UI {
	public static void main(String[] args) throws Exception {
		
		ObjectInputStream in=null;
		ObjectOutputStream out=null;
		Scanner scan = new Scanner(System.in);
		
		Person p;
		AddressBook ad = null;
		int menu=0;	//메뉴 선택 시 필요한 변수
		String name, phoneNum, address, email;
		
		
		//파일 read
		try {
			 in = new ObjectInputStream(new FileInputStream("AddressBook.dat"));
	         ad = new AddressBook(100, in);
	         in.close(); //in 닫기
		}catch (FileNotFoundException fnfe) {
			System.out.println("File not Found... First Time Start......");
		    ad = new AddressBook(100);
		}catch (Exception ex) {
			System.out.println("파일 관련 알수 없는 오류가 발생했습니다.");
		}


		loop:
		while(true)
		{
			
				System.out.println("----------------------------<  주소록 프로그램    >--------------------------------------------");
				System.out.println("  1.주소록 등록     2.주소록 검색      3.주소록 수정     4.주소록 삭제     5.전체 주소록 조회     6.주소록 저장     7.프로그램 종료");
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.print("원하시는 메뉴의 번호를 입력하세요. : ");
				
				//메뉴 선택-번호 입력 받음
				try{
					menu = scan.nextInt();	
					if(menu<0 || 7<menu) {		
						System.out.println("1부터 7까지의 숫자를 입력하세요.");
					}
				}catch(InputMismatchException e) {
						System.out.println("숫자를 입력해주세요.");
						scan.nextLine();
				}
				
				switch(menu) 
				{	//---------------------회원번호 등록------------------------------------------------
				case 1:
					System.out.println("============[ 주소록 등록  ]============");
					scan.nextLine();
					do { //(do-while)이름 겹치지 않게 입력하게 하는 코드
						System.out.print("이름을 입력하세요. : ");
						name = scan.nextLine();
						if(ad.checkName(name)==true) {
							System.out.println("이미 등록된 이름입니다. 이름 뒤에 숫자를 붙여 저장하세요. (ex:김한주1, 김한주2 ,김한주3)");
						}
						else break;
						}while(true);
					
					System.out.print("전화번호를 입력하세요. : ");
					phoneNum = scan.nextLine();
					if(ad.checkPhoneNum(phoneNum)==true) { //만약 등록된 전화번호일 경우 메뉴로 돌아가게 함
						System.out.println("이미 등록된 전화번호 입니다.");
						break;
					}
					System.out.print("주소를 입력하세요. : ");
					address = scan.nextLine();
					System.out.print("이메일을 입력하세요. : ");
					email = scan.nextLine();	
	
					try {
						ad.add(new Person(name,phoneNum,address,email));	//주소록 등록 메소드
					} catch (Exception e) {
						System.out.println("등록 가능 인원 수가 초과되었습니다.");			
						break;
					}
					
					System.out.println("\n주소록이 정상적으로 입력되었습니다.\n");
					
					break;
					
				//-------------------회원번호 검색------------------------------------------------------
				case 2: 	
					System.out.println("============[ 주소록 검색  ]============");
					System.out.print("1.이름으로 검색   2.전화번호로 검색 : ");
					do { //(do-while)메뉴 번호 제대로 입력하게 하는 코드
						int num=0;
						
						try{
							num = scan.nextInt();	//메뉴 번호 입력 받음
						}catch(InputMismatchException e) {
								System.out.print("숫자를 입력해야합니다. ");
								scan.nextLine();//
						}
							
						
						if(num==1) {//이름으로 검색-----------------------------------------------------
							System.out.print("검색하실 이름을 입력하세요. : ");
							scan.nextLine();
							name=scan.nextLine(); // 이름 입력 받음
	
							try {								
									int index=ad.searchName(name);
									System.out.println("< 주소록 번호 : "+index+" >");
									System.out.println("이름 : " + ad.getPerson(index).getName());
									System.out.println("전화번호 : " + ad.getPerson(index).getPhoneNum());
									System.out.println("집주소 : " + ad.getPerson(index).getAddress());
									System.out.println("이메일 : " + ad.getPerson(index).getEmail());
							} catch (Exception e) {
									System.out.println("등록된 이름이 없습니다.");			
							}
							break;
						}
						else if(num==2) {//전화번호로 검색-----------------------------------------------
							
							System.out.print("검색하실 전화번호를 입력하세요. : ");
							scan.nextLine();
							phoneNum=scan.nextLine();// 전화번호 입력 받음
							
							try {							
									int index=ad.searchPhoneNum(phoneNum);
									System.out.println("< 주소록 번호 : "+index+" >");
									System.out.println("이름 : " + ad.getPerson(index).getName());
									System.out.println("전화번호 : " + ad.getPerson(index).getPhoneNum());
									System.out.println("집주소 : " + ad.getPerson(index).getAddress());
									System.out.println("이메일 : " + ad.getPerson(index).getEmail());
							} catch (Exception e) {
								System.out.println("등록된 전화번호가 없습니다.");			
							}						
							break;
						}
						else {
							System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
							System.out.print("1.이름으로 검색   2.전화번호로 검색 : ");
						}
						}while(true);
					
					System.out.println("\n>>>");
					System.out.println("\n주소록 검색을 종료합니다.\n");
					
					break;
					
				case 3: 	//주소록 수정---------------------------------------------------------------
					System.out.println("============[ 주소록 수정  ]============");
					System.out.print("수정할 주소록의 회원 이름을 입력하세요 : ");
					scan.nextLine();

					try {					
							name=scan.nextLine();
							int index=ad.searchName(name); 
							
							do { //(do-while)이름 겹치지 않게 수정하는 코드
								System.out.print("이름을 입력하세요. : ");
								name = scan.nextLine();
								if(name.equals(ad.getPerson(index).getName())) {//이름 수정 안하는경우
									break;
								}
								else {
									if(ad.checkName(name)==true) {//이름 수정할 때, 등록된 이름일 경우
									System.out.println("이미 등록된 이름입니다. 이름 뒤에 숫자를 붙여 저장하세요. (ex:김덕선1, 김덕선2 ,김덕선3)");
									}
									else break;
								}
							}while(true);
					
							do { //(do-while)전화번호 겹치지 않게 수정하는 코드
								System.out.print("전화번호를 입력하세요. : ");
								phoneNum = scan.nextLine();
								if(phoneNum.equals(ad.getPerson(index).getPhoneNum())) {//전화번호 수정 안하는경우
									break;
								}
								else {
									if(ad.checkPhoneNum(phoneNum)==true) { //전화번호 수정할 때, 등록된 전화번호일 경우 
										System.out.println("이미 등록된 전화번호 입니다.");
									}
									else break;
								}	
							}while(true);
							
							System.out.print("주소를 입력하세요. : ");
							address = scan.nextLine();
							System.out.print("이메일을 입력하세요. : ");
							email = scan.nextLine();	
			
							p=new Person(name,phoneNum,address,email);	
											
							ad.modify(index,p); //주소록 수정
							System.out.println("수정이 완료되었습니다.");
							
					}catch (Exception e) {
							System.out.println("등록된 주소록이 없습니다.");			
					}
					break;
					
				case 4: // 주소록 삭제------------------------------------------------------------------
					System.out.println("============[ 주소록 삭제  ]============");
					
					if(ad.getCount() == 0) {
						System.out.println("등록된 주소록이 없습니다.");
						break;
					}
					
					System.out.print("삭제할 주소록의 회원 이름을 입력하세요 : ");
					scan.nextLine();

					try {					
							name=scan.nextLine();
							int index=ad.searchName(name); 
							ad.delete(index);
							System.out.println("해당 주소록이 삭제되었습니다.");
							
					}catch (Exception e) {
							System.out.println("등록된 주소록이 없습니다.");			
					}
					break;
					
				case 5: // 전체 주소록 조회 --------------------------------------------------------------
					System.out.println("============[ 전체 주소록 조회  ]============");
					int c=ad.getCount();
					if(c==0) {
						System.out.println("등록된 주소록이 없습니다.");
					}
					else {
						for(int i=0; i<c; i++) {
							System.out.println("< 주소록 번호 : "+i+" >");
							System.out.println("이름 : " + ad.getPerson(i).getName());
							System.out.println("전화번호 : " + ad.getPerson(i).getPhoneNum());
							System.out.println("집주소 : " + ad.getPerson(i).getAddress());
							System.out.println("이메일 : " + ad.getPerson(i).getEmail());
						}
					}
					break;
				
				case 6: // 주소록 저장------------------------------------------------------------------
					try {
						out = new ObjectOutputStream(new FileOutputStream("Addressbook.dat"));
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
					try {
						ad.writeFile(out);
						System.out.println("저장되었습니다.");
					} catch (FileNotFoundException fnfe) {
						System.out.println(fnfe.getMessage());
					} catch(IOException ioe) {
						System.out.println("저장을 실패했습니다.");
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
					
					finally {
						//close()이용해 out에 연결된 참조값을 끊기
						try {
							out.close();
						} catch (IOException ex) {
							System.out.println(ex.getMessage());
						} catch(Exception ex) {
							System.out.println(ex.getMessage());
						}
					}
					break;
					
				case 7:  // 프로그램 종료----------------------------------------------------------------
					System.out.println("============[ 프로그램을 종료합니다. ]============");
					scan.close(); 
					break loop;
					
				default:
					break;
					
			}//switch	
		}//while	
	}//main()
}//class UI


