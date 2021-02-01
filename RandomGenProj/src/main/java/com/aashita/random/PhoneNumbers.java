package com.aashita.random;

import java.util.Random;

public class PhoneNumbers implements IPhoneNumbers {
	static int MAXSIZE   = phonenums.length-1;
	static int MAXSIZEUS = usphonenums.length-1;
	private static int serviceCalled = 0;

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			getPhoneNumber();
		}
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
		String phonenum = usphonenums[id];
		

		System.out.println(serviceCalled++ + ") ["+ id +"]" + "\t:\t" + phonenum);


			return phonenum;
	}

}
