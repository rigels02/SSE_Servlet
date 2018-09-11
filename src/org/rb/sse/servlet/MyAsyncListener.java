package org.rb.sse.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;

public class MyAsyncListener implements AsyncListener {

	@Override
	public void onComplete(AsyncEvent e) throws IOException {
		System.out.println("MyAsyncListener onComplete... ");

	}

	@Override
	public void onError(AsyncEvent e) throws IOException {
		System.out.println("MyAsyncListener onError... ");

	}

	@Override
	public void onStartAsync(AsyncEvent e) throws IOException {
		System.out.println("MyAsyncListener onStartAsync... ");

	}

	@Override
	public void onTimeout(AsyncEvent e) throws IOException {
		System.out.println("MyAsyncListener onTimeout... ");
		ServletResponse response = e.getAsyncContext().getResponse();
		PrintWriter out = response.getWriter();
		out.write("Timeout Error on processing.... ");

	}

}
