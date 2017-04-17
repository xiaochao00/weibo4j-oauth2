package weibo4j.examples.testdemo.test1127.statustest;

import java.io.File;
import java.util.Date;

import weibo4j.examples.testdemo.test1127.utils.DateUtils;
import weibo4j.examples.testdemo.test1127.utils.SleepUtils;
import weibo4j.model.Paging;

public class SaveMessageTest {
	public StatusOptionTest sot = null;
	public SaveMessageTest(){
		sot = new StatusOptionTest();
	}
	public static void main(String []args){
		SaveMessageTest smt = new SaveMessageTest();
		//String timelineType = StatusBaseTest.Bilateral_Timeline;
		//获取20页的 验证用户关注的微博数据
		int sumNumber1 = 0;
//		sumNumber1 = smt.testSaveFriendsTimeline(100);
		//sumNumber1 = smt.testSaveFriendsTimelineByType(100,StatusBaseTest.Friends_Timeline);
		//int sumNumber2 = smt.testSaveBilateralTimeline(200);
		//sumNumber1 = smt.testSaveBilateralTimelineByType(140);
		//sumNumber1 = smt.testSavePublicTimelineByType(0, 200);
		sumNumber1 = smt.testSaveFriendsTimelineByType(20);
		System.out.println("保存的 博文数据有"+sumNumber1+"条记录");
		//System.out.println("保存的 相互关注的微博用户微博数据有"+sumNumber2+"条记录");
	}
	public int testSaveFriendsTimeline(int count){
		//int count = 10;//准备获取数据的总页数
		int sumNumber = 0; 
		int baseApp = 0;
		int feature = 1;//原创微博
		int pageNumber = 1;//页码
		int pageCountSize = 20;//每页记录数
		Paging paging = new Paging(pageNumber,pageCountSize);
		for(int i=1;i<=count;i++){
			sumNumber+=sot.saveFriendTimeline(baseApp, feature, paging);
			paging.setPage(paging.getPage()+1);
		}
		return sumNumber;
	}
	public int testSavePublicTimelineByType(){
		int number = sot.savePublicTimelineByType();
		return number;
	}
	public int testSavePublicTimelineByType(int baseApp,int count){
		int number = sot.savePublicTimelineByType(count, baseApp);
		return number;
	}
	public int testSaveFriendsTimelineByType(){
		int number = sot.saveFriendTimelineByType();
		return number;
	}
	public int testSaveFriendsTimelineByType(int count){
		//int count = 10;//准备获取数据的总页数
		int sumNumber = 0; 
		int baseApp = 0;
		int feature = 1;//原创微博
		int pageNumber = 1;//页码
		int pageCountSize = 10;//每页记录数
		Paging paging = new Paging(pageNumber,pageCountSize);
		for(int i=1;i<=count;i++){
			int returnNum = sot.saveFriendTimelineByType(baseApp, feature, paging);
			sumNumber+=returnNum;
			//如果返回记录是0 就没有必要继续获取了
			if(returnNum==0)
				break;
			paging.setPage(paging.getPage()+1);
		}
		return sumNumber;
	}
	public int testSaveBilateralTimeline(int count){
		//int count = 10;//准备获取数据的总页数
		int sumNumber = 0; 
		int baseApp = 0;
		int feature = 1;//原创微博
		int pageNumber = 1;//页码
		int pageCountSize = 20;//每页记录数
		Paging paging = new Paging(pageNumber,pageCountSize);
		for(int i=1;i<=count;i++){
			sumNumber+=sot.saveBilateralTimeline(baseApp, feature, paging);
			paging.setPage(paging.getPage()+1);
		}
		return sumNumber;
	}
	public int testSaveBilateralTimelineByType(int count){
		//int count = 10;//准备获取数据的总页数
		int sumNumber = 0; 
		int baseApp = 0;
		int feature = 1;//原创微博
		int pageNumber = 1;//页码
//		int pageCountSize = 20;//每页记录数
		int pageCountSize = 15;//每页记录数
		Paging paging = new Paging(pageNumber,pageCountSize);
		for(int i=1;i<=count;i++){
			int returnNum =	sot.saveBilateralTimelineByType(baseApp, feature, paging);
			sumNumber+= returnNum;
			paging.setPage(paging.getPage()+1); 
			if(returnNum==0){
				System.out.println("获取信息数目超过限制，当前计数是:"+sumNumber);
				break;
			}
			SleepUtils.sleep(1500);
		}
		return sumNumber;
	}
	
	public void saveLog(Date date1,Date date2,String timelineType){
		String fileName = StatusBaseTest.baseFilePath+"/"+timelineType+"/";
		StringBuffer sb = new StringBuffer();
		sb.append("beginTime:"+DateUtils.formatDateToString(date1)+"\r\n");
		sb.append("endTime:"+DateUtils.formatDateToString(date2)+"\r\n");
		sb.append("totalTime:"+DateUtils.subDate(date1, date2)+"\r\n");
		sb.append("保存最新的公共的 微博数据有:"+sot.sumFileNumber+"条记录"+"\r\n");
		sb.append("更新文件:"+sot.updateFileNumber+"条"+"\r\n");
		sb.append("添加文件:"+sot.addFileNumber+"条"+"\r\n");
		fileName = fileName + "log";
		if(!hasFile(fileName))
			createFile(fileName);
		fileName = fileName+"/log"+DateUtils.formatDateToString2(date2)+".txt";
		sot.saveLog(sb.toString(),fileName);
	}
	private boolean hasFile(String path){
		boolean flag = false;
		File file = new File(path);
		flag = file.exists();
		return flag;
	}
	private void createFile(String path){
		if(!hasFile(path)){
			File file = new File(path);
			file.mkdir();
		}
	}
}
