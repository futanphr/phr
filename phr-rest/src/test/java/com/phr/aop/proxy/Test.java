package com.phr.aop.proxy;

public class Test {
	public static void main(String[] args) {
		WorkerProxy wp=new WorkerProxy();
		Worker w=(Worker)wp.Bind(new WorkImpl());
		w.work();
		
	}
	

}
