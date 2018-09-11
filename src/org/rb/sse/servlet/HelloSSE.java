package org.rb.sse.servlet;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloSSE
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/sse" })
public class HelloSSE extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long startTime = System.currentTimeMillis();
		System.out.println("AsyncServlet, Thread: "+Thread.currentThread().getName());
		AsyncContext ac = request.startAsync();
		ac.addListener(new MyAsyncListener());
		//ac.setTimeout(5000);
		
		//Write required Header information
		response.setContentType("text/event-stream"); //<==
		response.setHeader("Cache-Control", "no-cache"); //<==
		//response.setHeader("Connection", "keep-alive");
		response.setCharacterEncoding("UTF-8");
		
		 //-----------//
		//executor.execute(new MySSEService(ac));
		 //Start async thread
		ac.start(new MySSEService(ac)); 
		long endTime = System.currentTimeMillis();
		
		System.out.println("SSE service started: "+Thread.currentThread().getName()+
				", starting time= "+(endTime-startTime)+" ms");
		
	}

	
}
