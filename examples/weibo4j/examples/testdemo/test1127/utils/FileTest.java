package weibo4j.examples.testdemo.test1127.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileTest {
	public static void main(String[]args){
		File f = new File("D:/weibo");
		//创建文件夹
		System.out.println(f.exists());
		if(!f.exists()){
			f.mkdir();
			System.out.println(f.exists());
		}
		File file = new File("d:/weibo/test.txt");
		try {
			FileWriter fw = new FileWriter(file,true);
			StringBuffer bf = new StringBuffer();
			bf.append("welcome你好 \r\n");
			bf.append("welcome \r\n");
			//fw.write(bf.toString());
			//fw.close();
			//AppendToFile.appendMethodB("d:/weibo/test.txt", bf.toString());
			AppendToFile.appendMethodC("d:/weibo/test.txt", bf.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
