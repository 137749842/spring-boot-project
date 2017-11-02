package com.longq.spring_boot_study.filter.util;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class MyOutputStream extends ServletOutputStream{

	private ServletOutputStream outputStream;  
	
	private String content;
    
    public MyOutputStream(ServletOutputStream outputStream) {  
        this.outputStream = outputStream;  
    }  
  
    @Override  
    public void write(int b) throws IOException {  
        outputStream.write(b);  
    }  
  
    @Override  
    public void write(byte[] b, int off, int len) throws IOException {  
    	outputStream.write(b, off, len);  
       content = new String(b);
    }  
  
    @Override  
    public void write(byte[] b) throws IOException {  
    	outputStream.write(b);  
    }

	@Override
	public boolean isReady() {
		return outputStream.isReady();
	}

	@Override
	public void setWriteListener(WriteListener arg0) {
		
	}

	public String getContent() {
		return content;
	}  
	
	
}
