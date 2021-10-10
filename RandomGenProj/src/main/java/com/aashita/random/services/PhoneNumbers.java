package com.aashita.random.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.aashita.random.data.IPhoneNumbers;


public class PhoneNumbers implements IPhoneNumbers {
	static int MAXSIZE   = phonenums.length-1;
	static List<Object> listUSPhNum = new ArrayList<Object>();
	static{
		listUSPhNum.addAll(Arrays.asList(usphonenums1));
		listUSPhNum.addAll(Arrays.asList(usphonenums2));
		listUSPhNum.addAll(Arrays.asList(usphonenums3));
		listUSPhNum.addAll(Arrays.asList(usphonenums4));
		listUSPhNum.addAll(Arrays.asList(usphonenums5));
		System.err.println("listUSPhNum.size()  = "+ listUSPhNum.size());
	}

	static int MAXSIZEUS = listUSPhNum.size();

	private static int serviceCalled = 0;
	
	public static void main(String[] args) {
	
		String str1, str2, str3;
		
		
		for (int i = 0; i < 100; i++) {
//			str1 = getUSPhoneNumber();
			str2 = getRandomPhNum();
		}
	}

	private static String getRandomPhNum() {
		Random ran = new Random();
		int n[] = new int[9];
		
		n[0] = (int) ran.nextInt(999);
		n[1] = (int) ran.nextInt(999);
		n[2] = (int) ran.nextInt(9999);
		
		String num = String.format("(%03d) %03d %04d", n[0], n[1], n[2]);
		System.err.println(num);
		return num; //"123 123 1234";
	}


	public static long getPhoneNumber() {
		Random ran = new Random();
		int id = ran.nextInt(MAXSIZE);
		long phonenum = phonenums[id];
		System.out.println(serviceCalled++ + ") ["+ id +"]" + "\t:\t" + phonenum);
		return phonenum;
	}

	public static String getUSPhoneNumber() {
		Random ran = new Random();
		int id = ran.nextInt(MAXSIZEUS);
		String phonenum = (String) listUSPhNum.get(id);

		System.out.println(serviceCalled++ + ") ["+ id +"]" + "\t:\t" + phonenum);
		return phonenum;
	}

}
