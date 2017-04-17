package weibo4j.examples.testdemo.test1127.statustest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ReadWriteFileWithEncode {
	public static final String DEFAULT_ENCODE = "UTF-8";
	/**
	 * 按照给定的字符集 写入文件
	 * 先删除再写入
	 * @param fileName
	 * @param content
	 * @param encode
	 * @throws IOException
	 */
	public static void writeByEncodie(String filePath,String content,String encode)throws IOException{
		if(encode==null||"".equals(encode))
			encode = DEFAULT_ENCODE;
		File file = new File(filePath);
		file.delete();
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),encode));
		writer.write(content);
		writer.close();
	}
	/**
	 * 按照指定编码集读文件
	 */
	public static String readFromEncode(String filePath,String encode) throws IOException{
		if(encode==null||"".equals(encode))
			encode = DEFAULT_ENCODE;
		File file = new File(filePath);
		String content = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),encode));
		String line = null;
		while((line=reader.readLine())!=null){
			content += line+"/n";
		}
		return content;
	}
	
}
