package org.rb.sse.servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloSSE
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/async" })
public class HelloAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource
	ManagedExecutorService executor;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long startTime = System.currentTimeMillis();
		System.out.println("AsyncServlet, Thread: "+Thread.currentThread().getName());
		AsyncContext ac = request.startAsync();
		ac.addListener(new MyAsyncListener());
		ac.setTimeout(5000);
		executor.execute(new MyAsyncService(ac));
		long endTime = System.currentTimeMillis();
		
		System.out.println("Async service started: "+Thread.currentThread().getName()+
				", starting time= "+(endTime-startTime)+" ms");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
