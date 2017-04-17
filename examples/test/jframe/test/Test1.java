package test.jframe.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Test1 {
	public static void main(String[]args){
		int num = 'ω';
		System.out.println("ω");
		System.out.println(num);
		File file = new File("examples/test/jframe/test/m.txt");
		Reader reader = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			reader = new InputStreamReader(fis);
			int tempchar;
			try {
				while ((tempchar = reader.read())!= -1){
				        if (((char) tempchar) != '\r') {
				        	if((char) tempchar!=' '){
				        		try {
				        			Thread.sleep(100);
				        		} catch (InterruptedException e) {
				        			// TODO Auto-generated catch block
				        			e.printStackTrace();
				        		}
				        	}
				            System.out.print((char) tempchar);
				        }
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}      
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
