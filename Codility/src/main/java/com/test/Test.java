package com.test;

import java.util.ArrayList;

public class Test {

	public static void main(String ...args){
		int i =0;
		method(i);
		
		ArrayList array = new ArrayList();
		ArrayList<String> stringarray = array;
		ArrayList<StringBuffer> stringBuffer = array;
		stringarray.add(0, "test");
		StringBuffer buff = stringBuffer.get(0);
		System.out.println(buff.toString());
		
		for (StringBuffer stringBuffer2 : stringBuffer) {
			System.out.println(stringBuffer2);
		}
	}
	private static void method(Object o){
		System.out.println("Object called");
	}

private static void method(String o){
	System.out.println("string called");
	}
}
