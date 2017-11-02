package com.longq.spring_boot_study.filter.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class MyWriterWrapper  extends HttpServletResponseWrapper{

	  private MyWriter myWriter;
	  private MyOutputStream myOutputStream;  
	  
	  public MyWriterWrapper(HttpServletResponse response) {  
	        super(response);  
	    }  
	      
	    @Override  
	    public PrintWriter getWriter() throws IOException {  
	    	if(myWriter ==null){
	    		myWriter = new MyWriter(super.getWriter());  
	    	}
	        return myWriter;  
	    }  
	      
	    @Override  
	    public ServletOutputStream getOutputStream() throws IOException {  
	    	if(myOutputStream ==null){
	    		 myOutputStream = new MyOutputStream(super.getOutputStream());  
	    	}
	        return myOutputStream;  
	    }  
	  
	    public MyWriter getMyWriter() {  
	        return myWriter;  
	    }  
	  
	    public MyOutputStream getMyOutputStream() {  
	        return myOutputStream;  
	    }  

}
