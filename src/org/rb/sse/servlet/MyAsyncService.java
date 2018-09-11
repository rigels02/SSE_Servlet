package org.rb.sse.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;

public class MyAsyncService implements Runnable {

	private AsyncContext ac;

	public MyAsyncService(AsyncContext ac) {
		this.ac = ac;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			PrintWriter out = ac.getResponse().getWriter();
			out.write("MyAsyncService completed long process...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ac.complete();

	}

}
