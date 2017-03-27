package com.digui;

import java.util.ArrayList;
import java.util.List;

public class Cal {
	public static Object[] data = new Object[100];
	static{
		for(int i=0; i<100; i++){
			data[i] = null;
			System.out.println(data[i]);
		}
	}
	public static void main(String[] args) {

	}
	
	public int cals(int i){
		if(i==0){
			data[0] = 0;
			return 0; 
		}else if(i==1){
			data[1] = 1;
			return 1;
		}else{
			if(data[i-1] == null){
				data[i-1] = cals(i-1);
			}
			if(data[i-2] == null){
				data[i-2] = cals(i-2);
			}
			return cals(i-1)+cals(i-2);
		}
	}
}
