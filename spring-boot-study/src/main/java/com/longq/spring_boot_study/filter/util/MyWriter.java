package com.longq.spring_boot_study.filter.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;

public class MyWriter  extends PrintWriter{

	private StringBuilder buffer;

	public MyWriter(Writer out) throws FileNotFoundException {
		 super(out);
	     buffer = new StringBuilder();
	}

    @Override
    public void write(char[] buf, int off, int len) {
        char[] dest = new char[len];
        System.arraycopy(buf, off, dest, 0, len);
        buffer.append(dest);
    }

    @Override
    public void write(char[] buf) {
        super.write(buf);
    }

    @Override
    public void write(int c) {
        super.write(c);
    }

    @Override
    public void write(String s, int off, int len) {
        super.write(s, off, len);
        buffer.append(s);
    }

    @Override
    public void write(String s) {
        super.write(s);
    }
    
    public String getContent(){
        return buffer.toString();
    }

}
