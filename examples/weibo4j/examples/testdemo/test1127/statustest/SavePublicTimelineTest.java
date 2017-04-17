package weibo4j.examples.testdemo.test1127.statustest;

import java.util.Date;

import weibo4j.examples.testdemo.test1127.utils.DateUtils;

/**
 * 保存公共用户的微博信息
 * @author Administrator
 *
 */
public class SavePublicTimelineTest {
	public static void main(String []args){
		Date date1 = new Date();
		SaveMessageTest smt = new SaveMessageTest();
		int sumNumber1 = smt.testSavePublicTimelineByType(0,200);
		Date date2 = new Date();
		
		System.out.println(DateUtils.formatDateToString(date1));
		System.out.println(DateUtils.formatDateToString(date2));
		System.out.println("历时:"+DateUtils.subDate(date1, date2));
		System.out.println("保存最新的公共的 博文数据有"+sumNumber1+"条记录");
		System.out.println("更新文件"+smt.sot.updateFileNumber+"条");
		System.out.println("添加文件"+smt.sot.addFileNumber+"条");
		//保存文件 把上面信息
		smt.saveLog(date1,date2,StatusBaseTest.Public_Timeline);
		
	}
}
