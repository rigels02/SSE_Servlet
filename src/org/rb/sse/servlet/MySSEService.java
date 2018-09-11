package org.rb.sse.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.AsyncContext;

public class MySSEService implements Runnable {

	private static final int LAST = 10;
	private AsyncContext ac;

	public MySSEService(AsyncContext ac) {
		this.ac = ac;
	}

	@Override
	public void run() {
		System.out.println("MySSEService starts....");
		long startTime = System.currentTimeMillis();
		try {
			
			PrintWriter out = ac.getResponse().getWriter();
			String msg;
			for(int i=0; i<LAST; i++) {
	            System.out.println(i);
	            
	            switch(i) {
	            case 0:
	            	msg="SSE info Start: "+(i+10);
	            	break;
	            case LAST-1:
	            	msg="SSE info End: "+(i+10);
	            	break;
	            default:
	            	msg="SSE info: "+(i+10);
	            }
	            //message (default) event
	            out.write("data: "+ msg +"\n\n");
	            //out.flush();
	            //other event named: info
	            out.write("event: info\n");
	            out.write("data: "+"other info "+i+"\n\n");
	            //json event named:json
	            out.write("event: json\n");
	            out.write("data: "+makeJson(i)+"\n\n");
	            
	            out.flush();
	            try {
	                Thread.sleep(2000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//IMPORTANT: close AsyncContext here
		ac.complete();
		long endTime = System.currentTimeMillis();
		System.out.println("MySSEService completed. Elapsed time: "+(endTime-startTime)+" ms");

	}
	
	private String makeJson(int ii) {
		
		JsonObject jsonObj = Json.createObjectBuilder()
				.add("name", "Name_" + ii)
				.add("age", ii)
				.build();
		return jsonObj.toString();
	}

}
