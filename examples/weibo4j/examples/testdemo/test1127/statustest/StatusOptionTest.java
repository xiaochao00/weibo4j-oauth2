package weibo4j.examples.testdemo.test1127.statustest;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import weibo4j.examples.testdemo.test1127.utils.AppendToFile;
import weibo4j.examples.testdemo.test1127.utils.DateUtils;
import weibo4j.model.Geos;
import weibo4j.model.Paging;
import weibo4j.model.Status;



public class StatusOptionTest extends StatusBaseTest{
//	public static String baseFilePath = "D:/weibo";
	private String timelineType =StatusBaseTest.Default;
	public int updateFileNumber = 0;
	public int addFileNumber = 0;
	public int sumFileNumber = 0;
	public static void main(String[]args){
		StatusOptionTest so = new StatusOptionTest();
		int pageNumber = 1;//第几页
		int pageCount = 20;//每页记录数
		Paging page = new Paging(pageNumber,pageCount);
		//so.testFriendsTimeLine(0, 1, page);
		//so.saveFriendTimeline(0, 1, page);
		//page.setPage(page.getPage()+1);
		//so.testFriendsTimeLine(0, 1, page);
		//so.testBilateralTimeline(0, 1, page);
		//page.setPage(page.getPage()+1);
		//so.testBilateralTimeline(0, 1, page);
		//so.testUserTimeline(0, 1, page);
		//page.setPage(page.getPage()+1);
		//so.testUserTimeline(0, 1, page);
//		so.testPublicTimeline();
		//so.testPublicTimeline(StatusBaseTest.Public_Timeline);
		//so.testPublicTimeline(100,0,StatusBaseTest.Public_Timeline);
		int length = 0;
//		length=so.savePublicTimelineByType(100, 0, StatusBaseTest.Public_Timeline);
		so.testUserTimelineByType(StatusBaseTest.User_Timeline);
		//length = so.saveFriendTimeline(0, 1, page);
		so.printLength(length);
	}
	/**
	 * * @param paging
	 *            相关分页参数
	 * @param 过滤类型ID
	 *            ，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
	 * @param baseAPP
	 * @param feature
	 * @param paging
	 */
	public void testFriendsTimeLine(Integer baseAPP, Integer feature,Paging paging){
		List<Status> statusList = getFriendsTimeLine(baseAPP,feature,paging);
		printStatus(statusList);
	}
	public void testBilateralTimeline(Integer baseAPP, Integer feature,Paging paging){
		List<Status> statusList = getBilateralTimeline(baseAPP,feature,paging);
		printStatus(statusList);
	}
	public void testUserTimeline(Integer baseAPP, Integer feature,Paging paging){
		List<Status> statusList = getUserTimeline(baseAPP,feature,paging);
		printStatus(statusList);
	}
	public void testUserTimelineByType(String timelineType){
		List<Status> statusList = getUserTimeline();
		printTimelineByType(statusList,timelineType);
	}
	public void testPublicTimeline(){
		List<Status> statusList = getPublicTimeline();
		printStatus(statusList);
	}
	public void testPublicTimeline(int count,int baseApp){
		timelineType = StatusBaseTest.Public_Timeline;
		List<Status> statusList =getPublicTimeline(count,baseApp);
		printTimelineByType(statusList,timelineType);
	}
	public void testPublicTimelineByType(){
		timelineType = StatusBaseTest.Public_Timeline;
		List<Status> statusList = getPublicTimeline();
		printTimelineByType(statusList,timelineType);
	}
	public void printTimelineByType(List<Status> statusList,String timelineType){
		if(statusList==null||statusList.size()<=0){
			printError();
			return ;
		}
		System.out.println("博文类型是:"+timelineType);
		printStatus(statusList);
	}
	private void printStatus(List<Status> statusList){
		if(statusList==null||statusList.size()<=0){
			printError();
			return ;
		}
		printLength(statusList.size());
		for(Status s:statusList){
			System.out.println("微博用户uid是："+s.getUser().getId());
			System.out.println("微博用户是昵称是:"+s.getUser().getScreenName());
			System.out.println("微博发布时间是："+DateUtils.formatDateToString(s.getCreatedAt()));
			System.out.println("微博发布内容是："+s.getText());
			System.out.println("微博评论数是："+s.getRepostsCount());
			System.out.println("微博转发数是："+s.getCommentsCount());
			System.out.println("发布微博地理位置是"+s.getGeo());
			System.out.println("纬度是"+s.getLatitude());//纬度
			System.out.println("经度是"+s.getLongitude());//经度
			String coordinate = s.getLongitude()+","+s.getLatitude();
			System.out.println("地理位置是："+geoToString(s.getGeo(),coordinate));//经度
		}
	}
	private void printError(){
		System.out.println("------------------本次查询长度是0-----------------");
	}
	private void printLength(int length){
		System.out.println("查询记录长度是："+length);
	}
	/**
	 * 从博文获取的地理消息中解析出具体的地址
	 * @param geo
	 * 博文地理坐标 经纬度，没有的话为空--但是在后台判断的时候总是通不过
	 * @param coordinate
	 * 经纬度组合 经度，纬度
	 * @return
	 */
	private String geoToString(String geo,String coordinate){
		List<Geos> geosList = geoToAddress(geo,coordinate);
		String address = "";
		if(geosList!=null){
			for(Geos g:geosList){
				address = address+g.getAddress();
			}
		}
		return address;
	}
	public String getTimelineString(List<Status> statusList){
		if(statusList==null||statusList.size()==0)
			return null;
		StringBuffer content = new StringBuffer();
		for(Status s:statusList){
			content.append("微博用户uid是："+s.getUser().getId()+"  \r\n");
			content.append("微博用户是昵称是"+s.getUser().getScreenName()+"  \r\n");
			content.append("微博发布时间是："+DateUtils.formatDateToString(s.getCreatedAt())+"\r\n");
			content.append("微博发布内容是："+s.getText()+"\r\n");
			content.append("微博评论数是："+s.getRepostsCount()+"\r\n");
			content.append("微博转发数是："+s.getCommentsCount()+"\r\n");
			String coordinate = s.getLongitude()+","+s.getLatitude();
			content.append("地理位置是："+geoToString(s.getGeo(),coordinate)+"\r\n");
		}
		return content.toString();
	}
	public String getContent(Integer baseAPP,Integer feature,Paging paging){
		String content = null;
		List<Status> statusList = getFriendsTimeLine(baseAPP,feature,paging);
		if(statusList!=null&&statusList.size()>0){
			content = getTimelineString(statusList);
		}else{
			printError();
		}
		return content;
	}
	public void saveLog(String content,String fileName){
		AppendToFile.appendMethodC(fileName, content.toString());
	}
	/**
	 * 默认的保存 当前用户及其所关注用户的博文
	 * @return
	 */
	public int saveFriendTimelineByType(){
		timelineType = StatusBaseTest.Friends_Timeline;
		List<Status> statusList = getFriendsTimeLine();
		int count = saveTimelineByType(statusList,timelineType);
		return count;
	}
	/**
	 * 获取当前登录用户及其所关注（授权）用户的最新微博
	 * @param baseAPP
	 * 是否获取当前应用的数据
	 * @param feature
	 * 是否是原创的
	 * @param paging
	 * 分页信息
	 * return count
	 * 		保存的记录数目
	 * 最多单次获取 150条记录
	 */
	public int saveFriendTimeline(Integer baseAPP,Integer feature,Paging paging){
		List<Status> statusList = getFriendsTimeLine(baseAPP,feature,paging);
		int count = saveTimeLine(statusList);
		return count;
	}
	public int saveFriendTimelineByType(Integer baseAPP,Integer feature,Paging paging){
		timelineType = StatusBaseTest.Friends_Timeline;
		List<Status> statusList = getFriendsTimeLine(baseAPP,feature,paging);
		int count = saveTimelineByType(statusList,timelineType);
		return count;
	} 
	/**
	 * 获取双向关注用户的最新微博
	 * @param baseAPP
	 * 
	 * @param feature
	 * 是否是原创的
	 * @param paging
	 * 分页信息保存有 每页记录数 查询的页码
	 * @return
	 */
	public int saveBilateralTimeline(Integer baseAPP,Integer feature,Paging paging){
		List<Status> statusList = getBilateralTimeline(baseAPP,feature,paging);
		return saveTimeLine(statusList);
	}
	public int saveBilateralTimelineByType(Integer baseAPP,Integer feature,Paging paging){
		timelineType = StatusBaseTest.Bilateral_Timeline;
		List<Status> statusList = getBilateralTimeline(baseAPP,feature,paging);
		return saveTimelineByType(statusList,timelineType);
	}
	public int savePublicTimelineByType(){
		timelineType = StatusBaseTest.Public_Timeline;
		List<Status> statusList = getPublicTimeline();
		return saveTimelineByType(statusList,timelineType);
	}
	public int savePublicTimelineByType(int count,int baseApp){
		timelineType = StatusBaseTest.Public_Timeline;
		List<Status> statusList = getPublicTimeline(count,baseApp);
		return saveTimelineByType(statusList,timelineType);
	}
	private int saveTimeLine(List<Status> statusList){
		int count = 0;
		if(statusList==null||statusList.size()<=0){
			return 0;
		}
		for(Status s:statusList){
			StringBuffer content = new StringBuffer();
			String uid = s.getUser().getId();
			String wid = s.getId();
			content.append("微博用户uid是："+uid);
			content.append(" \r\n");
			content.append("微博用户是昵称是:"+s.getUser().getScreenName());
			content.append("\r\n");
			content.append("博文id是:"+wid);
			content.append("\r\n");
			content.append("微博发布时间是："+DateUtils.formatDateToString(s.getCreatedAt()));
			content.append("\r\n");
			content.append("微博发布内容是："+s.getText());
			content.append("\r\n");
			content.append("微博评论数是："+s.getRepostsCount());
			content.append("\r\n");
			content.append("微博转发数是："+s.getCommentsCount());
			content.append("\r\n");
			String coordinate = s.getLongitude()+","+s.getLatitude();
			content.append("地理位置是："+geoToString(s.getGeo(),coordinate));
			String dirPath = baseFilePath;
			String dirPath2 = baseFilePath+"/"+uid;
			createFile(dirPath);//创建目录
			createFile(dirPath2);//创建二级目录
			String fileName = dirPath2+"/"+wid+".txt";
//			if(hasFile(fileName))
//				AppendToFile.appendMethodC(fileName, content.toString());
//			else
//				AppendToFile.appendMethodB(fileName, content.toString());
			// 如果原来存在这个博文信息的话 就作为更新操作 即覆盖原来的
			AppendToFile.appendMethodC(fileName, content.toString());
			count++;
		}
		return count;
	}
	private int saveTimelineByType(List<Status> statusList,String timelineType){
		int count = 0;
		if(statusList==null||statusList.size()<=0){
			return 0;
		}
		for(Status s:statusList){
			StringBuffer content = new StringBuffer();
			String uid = s.getUser().getId();
			String wid = s.getId();
			content.append("uid:"+uid);
			content.append(" \r\n");
			content.append("screenName:"+s.getUser().getScreenName());
			content.append("\r\n");
			content.append("ulocation:"+s.getUser().getLocation());
			content.append("\r\n");
			content.append("mid:"+wid);
			content.append("\r\n");
			content.append("createAt:"+DateUtils.formatDateToString(s.getCreatedAt()));
			content.append("\r\n");
			content.append("text:"+s.getText());
			content.append("\r\n");
			content.append("reposeCount:"+s.getRepostsCount());
			content.append("\r\n");
			content.append("commentCount:"+s.getCommentsCount());
			content.append("\r\n");
			String coordinate = s.getLongitude()+","+s.getLatitude();
//			content.append("location:"+geoToString(s.getGeo(),coordinate));
			String dirPath = baseFilePath;
			String dirPath2 = baseFilePath+"/"+timelineType;
			String dirPath3 = dirPath2+"/"+uid;
			createFile(dirPath);//创建目录
			createFile(dirPath2);//创建二级目录
			createFile(dirPath3);//创建三级目录
			
			String fileName = dirPath3+"/"+wid+".txt";
//			if(hasFile(fileName))
//				AppendToFile.appendMethodC(fileName, content.toString());
//			else
//				AppendToFile.appendMethodB(fileName, content.toString());
			// 如果原来存在这个博文信息的话 就作为更新操作 即覆盖原来的
			if(hasFile(fileName))
				updateFileNumber++;
			else
				addFileNumber++;
			sumFileNumber++;
//			AppendToFile.appendMethodC(fileName, content.toString());
			try {
				ReadWriteFileWithEncode.writeByEncodie(fileName, content.toString(), ReadWriteFileWithEncode.DEFAULT_ENCODE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
		}
		return count;
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
