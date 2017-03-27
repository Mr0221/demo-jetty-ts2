package com.Fibonacci;

public class Solution {
	public Object[] data = new Object[40];
	public int Fibonacci(int n) {
		if(n==0){
			return (Integer) (data[0]=0);
		}else if(n==1){
			return (Integer)(data[1]=1);
		}else{
			if(data[n-1] == null){
				data[n-1] = Fibonacci(n-1);
			}
			if(data[n-2] == null){
				data[n-2] = Fibonacci(n-2);
			}
			return ( (Integer)(data[n]= (Integer)data[n-1]+ (Integer)data[n-2]));
		}
	}
}
