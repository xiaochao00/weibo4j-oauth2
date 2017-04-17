package weibo4j.examples.testdemo.test1127.usertest;

import java.util.List;

import weibo4j.Account;
import weibo4j.Friendships;
import weibo4j.Users;
import weibo4j.model.Paging;
import weibo4j.model.User;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;
import weibo4j.util.WeiboConfig;
/**
 * 用户个人信息操作的公共代理工具类
 * @author Administrator
 *
 */
public class UserBaseTest {
	
	private String access_token;//令牌
	private String uid;//用户id
	private Friendships fm;//所有好友的封装类
	private Users um ;//所有用户的封装类
	//把它们作为全局变量的原因是由于在下面的应用方法中多次用到，
	//并且每次用到会发送一次请求
	//为了降低请求频次
	public UserBaseTest(){
		access_token = getAccess_Token();
		uid = getUid();
		fm = new Friendships(access_token);
		um = new Users(access_token);
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
	 * 通过用户uid得到用户信息
	 * @param uid
	 * @return
	 * 限制 必须是自己的粉丝
	 */
	public User getUserById(String uid){
		User user = null;
		try {
			user = um.showUserById(uid);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return user;
	}
	/**
	 * 通过用户的uid得到用户的粉丝列表
	 * @param uid
	 * 限制必须为 授权用户
	 * @return
	 */
	public List<User> getFollowersById(String uid){
		List<User> userList = null;
		try {
			UserWapper users = fm.getFollowersById(uid);
			userList = users.getUsers();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}
	/**
	 * 获取当前授权用户的粉丝列表
	 * @param uid
	 * 限制 3条记录
	 * @return
	 */
	public List<User> getFollowers(){
		List<User> userList = null;
		try {
			UserWapper users = fm.getFollowersById(uid);
			userList = users.getUsers();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}
	/**
	 * 得到用户的粉丝id值
	 * @return
	 * 限制78
	 * 
	 */
	public String[] getFollowersIds(){
		String[] followers = null;
		try {
			followers = fm.getFollowersIdsById(uid);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return followers;
	}
	/**
	 * 返回当前授权的用户
	 */
	public User getAccessUser(){
		String uid = getUid();
		return getUserById(uid);
	}
	/**
	 * 获取授权用户的关注列表----有限制
	 * 限制 15条记录
	 */
	public List<User> getFriends(){
		List<User> userList = null;
		try {
			UserWapper users = fm.getFriendsByID(uid);
			userList = users.getUsers();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}
	/**
	 * 获取用户关注的用户的 id数组
	 * 限制长度 88
	 * 可以在调用 getUserById(uid)方法获取用户的信息
	 * @return
	 */
	public String[] getFriendsIds(){
		String[] friendsIdList = null;
		try {
			String[] ids = fm.getFriendsIdsByUid(uid);
			friendsIdList = ids;
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return friendsIdList;
	}
	/**
	 * 得到 共同关注的用户列表
	 * 限制：
	 * @return
	 */
	public List<User> getFriendsBilateral(){
		List<User> userList = null; 
		try {
			UserWapper users = fm.getFriendsBilateral(uid);
			//String[] ids = fm.getFriendsBilateralIds(uid);
			userList = users.getUsers();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}
	/**
	 * 
	 * @param pageNumber
	 *            返回结果的页码，默认为1。
	 * @param sort
	 *            排序类型，0：按关注时间最近排序，默认为0。
	 * @return
	 */
	public List<User> getFriendsBilateral(Integer sort, int pageNumber){
		List<User> userList = null;
		Paging paging = new Paging(pageNumber);
		try {
			UserWapper users = fm.getFriendsBilateral(uid,sort,paging);
			userList = users.getUsers();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}
	/**
	 * 
	 * @param sort
	 * 				排序类型，0：按关注时间最近排序，默认为0。
	 * @param page
	 * 				页码对象，存有页码，每页记录号
	 * @return
	 */
	public List<User> getFriendsBilateral(Integer sort,Paging page){
		List<User> userList = null;
		UserWapper users;
		try {
			users = fm.getFriendsBilateral(uid,sort,page);
			userList = users.getUsers();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}
	/**
	 * 获取用户共同关注的用户列表
	 * 限制
	 * @return
	 */
	public String[] getFriendsBilateralIds(){
		String[] friendsBilateralIds = null;
		try {
			String[] ids = fm.getFriendsBilateralIds(uid);
			friendsBilateralIds = ids;
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return friendsBilateralIds;
	}
}
