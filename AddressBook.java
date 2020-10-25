package address;

import java.io.*;
import java.util.*;


public class AddressBook {
	ObjectOutputStream fOut = null; //파일 관리 변수중 write에 쓰이는 변수 fOut
	ObjectInputStream fIn = null; //파일 관리 변수중 read에 쓰이는 변수 fIn
	private ArrayList<Person> list;  //ArrayList 객체 선언
	
	//불러올 파일이 없는 경우
	public AddressBook(int num) throws Exception { //UI에서 생성할 사람의 수 가져와서 객체 생성
		list = new ArrayList<Person>(num);
	}
	
	//저장된 파일이 있는 경우
	public AddressBook(int num, ObjectInputStream fn) throws Exception { //UI에서 생성할 사람의 수 가져와서 객체 생성
		fIn = fn;
		list = new ArrayList<Person>(num);	//주소록 객체 생성
		try {
			readFile(fIn);	// 파일 불러오기
		}
		catch (IOException e) {
			throw new IOException("IOException");
		}catch(NullPointerException e) {
			throw new IOException("NullPointerException");
		}
	}

	public int getCount(){	//등록된 사람 수 접근자
		return list.size();
	}
	
	//동명이인 확인 메소드
	public boolean checkName(String name) {
		int count = list.size();	//리스트의 데이터 수를 count 변수에 저장
		for(int i=0;i<count;i++) 
		{
			if(name.equals(list.get(i).getName())) {//등록된 이름이 있을 때 true 리턴
				return true;
			}
		}
		return false;
	}
	
	//등록된 전화번호가 있는지 확인 메소드
	public boolean checkPhoneNum(String phoneNum) {
		int count = list.size();	//리스트 개수를 count 변수에 저장
		for(int i=0;i<count;i++) 
		{
			if(phoneNum.equals(list.get(i).getPhoneNum())) {//등록된 전화번호가 있을 때 true 리턴
				return true;
			}
		}
		return false;
	}
	
	//주소록 등록 메소드
	public void add(Person p)throws Exception{ 
	  try {
		  list.add(p);		//list에 Person객체 저장
	  }catch (Exception e) {
		  throw new ArrayIndexOutOfBoundsException("add 불가");
	  }
	}

	//이름으로 주소록 번호 검색 메소드, 등록된 이름 없을 경우 익셉션 
	public int searchName(String name) throws Exception{
		int count = list.size();	//리스트의 데이터 수를 count 변수에 저장
		for(int i=0;i<count;i++) 
		{
			if(name.equals(list.get(i).getName())) {//등록된 이름이 있을 때,  회원번호(인덱스 번호)리턴
				return i;
			}
		}
			throw new Exception("등록된 name 없음");
			
		
	}	

	//전화번호로 주소록 번호 검색 메소드, 등록된 전화번호 없을 경우 익셉션 
	public int searchPhoneNum(String phoneNum)throws Exception{
		int count = list.size();	//리스트의 데이터 수를 count 변수에 저장
		for(int i=0;i<count;i++){
			if(phoneNum.equals(list.get(i).getPhoneNum())){//등록된 전화번호 있을 때, 회원번호(인덱스 번호)리턴
				return i;
			}	
		}
		throw new Exception("등록된 phoneNum 없음");
	}

	//주소록 수정 메소드
	public void modify(int index, Person p){
		list.set(index, p);
	}

	//주소록 삭제 메소드
	public void delete(int index){  
		list.remove(index);
	}
	//Person 객체 넘겨주는 메소드
	public Person getPerson(int index){
		return list.get(index);
	}
	
	//데이터를 파일에 저장하는 메소드
	public void writeFile(ObjectOutputStream fn) throws Exception{
		try {
			int count = list.size();	//리스트의 데이터 수를 count 변수에 저장
			for(int i=0;i<count;i++){
				try {
				//persons배열낸 Person 객체에 하나씩 접근해 저장
					fn.writeObject(list.get(i));		// 객체 직렬화
				}catch(Exception e) {
					throw new Exception("writeFile Exception");
				}
			}

		} catch (IOException ioe) {
			throw new IOException("IOException");
		}		
	}	
	
	//데이터를 파일에서 가져오는 메소드
	//자료형에 따라 read함수를 다르게 사용
	public void readFile(ObjectInputStream fn) throws Exception{
		try {
			while(true) {
				    Person p = new Person();	//read한 정보를 저장할 Person객체 생성
				    p = (Person) fn.readObject();	//객체 역직렬화
				    list.add(p);
			}	
		}catch (EOFException eofe) {	// 파일을 다 읽어온 경우
			
		}catch (IOException ioe) {		// 파일을 읽을 수 없는 경우
			throw new IOException("IOException");
		}
	}
}
