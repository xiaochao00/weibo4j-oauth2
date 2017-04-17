package shmtu.information.weibo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weibo4j.Account;
import weibo4j.Location;
import weibo4j.Timeline;
import weibo4j.examples.testdemo.test1127.utils.SleepUtils;
import weibo4j.model.Geos;
import weibo4j.model.Paging;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;
import weibo4j.util.WeiboConfig;

public class StatusBaseTestAuto {
	public static final String Friends_Timeline = "friendsTimeline";//获取当前登录用户及其所关注用户的最新微博
	public static final String Friends_Timeline_Ids = "friendsTimelineIds";//获取当前登录用户及其所关注用户的最新微博的ID
	public static final String User_Timeline  = "userTimeline";//获取用户发布的微博
	public static final String Bilateral_Timeline = "bilateralTimeline";//获取双向关注用户的最新微博
	public static final String Public_Timeline = "publicTimeline";//获取双向关注用户的最新微博
	public static final String Default = "default";//获取双向关注用户的最新微博
	
	public static final String baseFilePath = "D:/weibo";
	private String access_token;
	private String uid;
	private Timeline tm;
	private Location location;
	public StatusBaseTestAuto(){
		access_token = getAccess_Token();
		uid = getUid();
		tm = new Timeline(access_token);
		location = new Location(access_token);
	}
	/**
	 * 得到 access_token--设置成私有方法
	 */
	private String getAccess_Token(){
		return WeiboConfig.getValue("access_token");
	}
	/**
	 * 获取授权用户的uid
	 * @param access_token
	 * @return
	 */
	private String getUid(){
		Account am = new Account(access_token);
		JSONObject uid;
		String uidStr = null;
		try {
			uid = am.getUid();
			try {
				uidStr = uid.get("uid").toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return uidStr;
	}
	/**
	 * 获取当前登录用户及其所关注用户的最新微博消息。<br/>
	 * 和用户登录 http://weibo.com 后在“我的首页”中看到的内容相同。
	 * 每页20记录的时候 只能最多返回150条记录
	 * 设置每页50条的时候也是150条返回记录
	 * 只返回授权用户的微博，非授权用户的微博将不返回
	 * 使用官方移动SDK调用，可多返回30%的非授权用户的微博
	 * @return
	 */
	public List<Status> getFriendsTimeLine(){
		List<Status> statusList = null;
		try {
			StatusWapper status = tm.getFriendsTimeline();
			statusList = status.getStatuses();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statusList;
	}
	public List<Status> getFriendsTimeLine(Integer baseAPP, Integer feature,Paging paging){
		List<Status> statusList = null;
		try {
			StatusWapper status = tm.getFriendsTimeline(baseAPP,feature,paging);
			statusList = status.getStatuses();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statusList;
	}
	/**
	 * 获取双向关注用户的最新微博
	 * 
	 * @return
	 */
	public List<Status> getBilateralTimeline(){
		List<Status> statusList = null;
		try {
			StatusWapper status = tm.getBilateralTimeline();
			statusList = status.getStatuses();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statusList;
	}
	public List<Status> getBilateralTimeline(Integer baseAPP, Integer feature,Paging paging){
		Map<String,String> map = new HashMap<String,String>();
		map.put("base_app", baseAPP.toString());
		map.put("feature", feature.toString());
		map.put("page", Integer.toString(paging.getPage()));
		map.put("count", Integer.toString(paging.getCount()));
		List<Status> statusList = null;
		try {
			StatusWapper status = tm.getBilateralTimeline(map);
			statusList = status.getStatuses();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statusList;
	}
	/**
	 * 获取某个用户最新发表的微博列表--
	 * 限制只能是本人的
	 * 单次5条记录
	 * 并且最多10条记录
	 * 带有参数的没用
	 * @return
	 */
	public List<Status> getUserTimeline(){
		List<Status> statusList = null;
		try {
			StatusWapper status = tm.getUserTimeline();
			statusList = status.getStatuses();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statusList;
	}
	//不可用的
	public List<Status> getUserTimeline(Integer base_app, Integer feature,Paging paging){
		Map<String,String> map = new HashMap<String,String>();
		map.put("base_app", base_app.toString());
		map.put("feature", feature.toString());
		map.put("page", Integer.toString(paging.getPage()));
		map.put("count", Integer.toString(paging.getCount()));
		List<Status> statusList = null;
		try {
			//StatusWapper status = tm.getUserTimeline(map);
			//StatusWapper status = tm.getUserTimeline();
			StatusWapper status = tm.getUserTimelineByUid(uid,paging,base_app,feature);
			statusList = status.getStatuses();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statusList;
	}
	/**
	 * 返回最新的公共微博
	 * 可以通过传递参数count控制每页的记录数目，传递200的时候是196条，100的时候是95
	 * 但是获取公共微博的信息有很大的 没用信息因此，保存方法里面采用了保存博文类型的文件夹
	 * 建议的频繁度 一天可以进行多次，具体次数需要在考虑
	 * @return
	 */
	public List<Status> getPublicTimeline(){
		List<Status> statusList = null;
		try {
			StatusWapper status = tm.getPublicTimeline();
			statusList = status.getStatuses();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statusList;
	}
	public List<Status> getPublicTimeline(int count, int baseApp){
		List<Status> statusList = null;
		try {
			StatusWapper status = tm.getPublicTimeline(count,baseApp);
			statusList = status.getStatuses();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statusList;
	}
	//
	public List<Geos> geoToAddress(String geo,String coordinate){
		if(geo.equals(null)||coordinate.equals("-1.0,-1.0"))
			return null;
		//String coordinate = geos.getLongitude()+","+geos.getLatitude();
		List<Geos> list = null;
		try {
//			SleepUtils.sleep(800);//每次访问地理信息前 等待一秒
			SleepUtils.sleep(1000);//每次访问地理信息前 等待一秒
			list = location.geoToAddress(coordinate);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
