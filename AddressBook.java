package address;

import java.io.*;
import java.util.*;


public class AddressBook {
	ObjectOutputStream fOut = null; //���� ���� ������ write�� ���̴� ���� fOut
	ObjectInputStream fIn = null; //���� ���� ������ read�� ���̴� ���� fIn
	private ArrayList<Person> list;  //ArrayList ��ü ����
	
	//�ҷ��� ������ ���� ���
	public AddressBook(int num) throws Exception { //UI���� ������ ����� �� �����ͼ� ��ü ����
		list = new ArrayList<Person>(num);
	}
	
	//����� ������ �ִ� ���
	public AddressBook(int num, ObjectInputStream fn) throws Exception { //UI���� ������ ����� �� �����ͼ� ��ü ����
		fIn = fn;
		list = new ArrayList<Person>(num);	//�ּҷ� ��ü ����
		try {
			readFile(fIn);	// ���� �ҷ�����
		}
		catch (IOException e) {
			throw new IOException("IOException");
		}catch(NullPointerException e) {
			throw new IOException("NullPointerException");
		}
	}

	public int getCount(){	//��ϵ� ��� �� ������
		return list.size();
	}
	
	//�������� Ȯ�� �޼ҵ�
	public boolean checkName(String name) {
		int count = list.size();	//����Ʈ�� ������ ���� count ������ ����
		for(int i=0;i<count;i++) 
		{
			if(name.equals(list.get(i).getName())) {//��ϵ� �̸��� ���� �� true ����
				return true;
			}
		}
		return false;
	}
	
	//��ϵ� ��ȭ��ȣ�� �ִ��� Ȯ�� �޼ҵ�
	public boolean checkPhoneNum(String phoneNum) {
		int count = list.size();	//����Ʈ ������ count ������ ����
		for(int i=0;i<count;i++) 
		{
			if(phoneNum.equals(list.get(i).getPhoneNum())) {//��ϵ� ��ȭ��ȣ�� ���� �� true ����
				return true;
			}
		}
		return false;
	}
	
	//�ּҷ� ��� �޼ҵ�
	public void add(Person p)throws Exception{ 
	  try {
		  list.add(p);		//list�� Person��ü ����
	  }catch (Exception e) {
		  throw new ArrayIndexOutOfBoundsException("add �Ұ�");
	  }
	}

	//�̸����� �ּҷ� ��ȣ �˻� �޼ҵ�, ��ϵ� �̸� ���� ��� �ͼ��� 
	public int searchName(String name) throws Exception{
		int count = list.size();	//����Ʈ�� ������ ���� count ������ ����
		for(int i=0;i<count;i++) 
		{
			if(name.equals(list.get(i).getName())) {//��ϵ� �̸��� ���� ��,  ȸ����ȣ(�ε��� ��ȣ)����
				return i;
			}
		}
			throw new Exception("��ϵ� name ����");
			
		
	}	

	//��ȭ��ȣ�� �ּҷ� ��ȣ �˻� �޼ҵ�, ��ϵ� ��ȭ��ȣ ���� ��� �ͼ��� 
	public int searchPhoneNum(String phoneNum)throws Exception{
		int count = list.size();	//����Ʈ�� ������ ���� count ������ ����
		for(int i=0;i<count;i++){
			if(phoneNum.equals(list.get(i).getPhoneNum())){//��ϵ� ��ȭ��ȣ ���� ��, ȸ����ȣ(�ε��� ��ȣ)����
				return i;
			}	
		}
		throw new Exception("��ϵ� phoneNum ����");
	}

	//�ּҷ� ���� �޼ҵ�
	public void modify(int index, Person p){
		list.set(index, p);
	}

	//�ּҷ� ���� �޼ҵ�
	public void delete(int index){  
		list.remove(index);
	}
	//Person ��ü �Ѱ��ִ� �޼ҵ�
	public Person getPerson(int index){
		return list.get(index);
	}
	
	//�����͸� ���Ͽ� �����ϴ� �޼ҵ�
	public void writeFile(ObjectOutputStream fn) throws Exception{
		try {
			int count = list.size();	//����Ʈ�� ������ ���� count ������ ����
			for(int i=0;i<count;i++){
				try {
				//persons�迭�� Person ��ü�� �ϳ��� ������ ����
					fn.writeObject(list.get(i));		// ��ü ����ȭ
				}catch(Exception e) {
					throw new Exception("writeFile Exception");
				}
			}

		} catch (IOException ioe) {
			throw new IOException("IOException");
		}		
	}	
	
	//�����͸� ���Ͽ��� �������� �޼ҵ�
	//�ڷ����� ���� read�Լ��� �ٸ��� ���
	public void readFile(ObjectInputStream fn) throws Exception{
		try {
			while(true) {
				    Person p = new Person();	//read�� ������ ������ Person��ü ����
				    p = (Person) fn.readObject();	//��ü ������ȭ
				    list.add(p);
			}	
		}catch (EOFException eofe) {	// ������ �� �о�� ���
			
		}catch (IOException ioe) {		// ������ ���� �� ���� ���
			throw new IOException("IOException");
		}
	}
}
