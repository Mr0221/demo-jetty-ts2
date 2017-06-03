package com.mr.synchronzied.demo1;

public class SingletonInner {
	
	private static class Singletion {
		private static Singletion single = new Singletion();
	}
	
	public static Singletion getInstance(){
		return Singletion.single;
	}
	
}
