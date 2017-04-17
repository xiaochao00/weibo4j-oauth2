package weibo4j.examples.testdemo.test1127.usertest;

import java.util.List;

import weibo4j.model.Paging;
import weibo4j.model.User;

public class UserOptionTest extends UserBaseTest{
	public static void main(String args[]){
		UserOptionTest uo = new UserOptionTest();
		//System.out.println(uo.getUid());
		//测试粉丝
		//uo.testFollowers();----3条记录
		//测试关注的用户
		//uo.testFriends();-----15条记录
		//测试用户关注ids
		//uo.testFriendsIds();------88条记录 但是不能获取详细信息
		//测试一个指定用户的信息
		//User user = uo.getUserById("3021514657");
		//uo.testUserById("2673655073");
		//测试获取用户粉丝ids
		//uo.testFollowersIds();78条记录
		//uo.testFriendsBilateralIds();//----15条记录
		//uo.testFriendsBilateral();//----15条记录
		//测试分页的获取共同关注的用户
		//uo.testFriendsBilateral(0, 2);
		int pageNumber = 1;
		int count = 30;//--------真是获取的数目是这个数字的30%
		Paging page = new Paging(pageNumber,count);
		uo.testFriendsBilateral(0, page);
	}
	public void testUserById(String uid){
		User user = getUserById(uid);
		System.out.println("--------------------");
		System.out.println("获取用户uid是:"+uid);
		if(user==null)
			return ;
		System.out.println("获取用户昵称是:"+user.getScreenName());
	}
	public void testFollowers(){
		List<User> userList = getFollowers();
		printList(userList);
	}
	public void testFriends(){
		List<User> userList = getFriends();
		printList(userList);
	}
	public void testFriendsBilateral(){
		List<User> userList = getFriendsBilateral();
		printList(userList);
	}
	public void testFriendsBilateral(Integer sort, int pageNumber){
		List<User> userList = getFriendsBilateral(sort,pageNumber);
		printList(userList);
	}
	public void testFriendsBilateral(Integer sort, Paging page){
		List<User> userList = getFriendsBilateral(sort,page);
		printList(userList);
	}
	public void testFriendsIds(){
		String[] friendsIds = getFriendsIds();
		printStringArray(friendsIds);
	}
	public void testFollowersIds(){
		String []followersIds = getFollowersIds();
		printStringArray(followersIds);
	}
	public void testFriendsBilateralIds(){
		String[] friendsBilateralIds = getFriendsBilateralIds();
		printStringArray(friendsBilateralIds);
	}
	public void printList(List<User> userList){
		if(userList==null||userList.size()<=0){
			System.out.println("获取用户信息记录为空，请检查");
			return ;
		}
		System.out.println("--------------------------------------------");
		System.out.println("获取用户信息记录数目是： "+userList.size());
		for(User user:userList){
			System.out.println("用户的uid："+user.getId());
			System.out.println("用户的昵称是："+user.getScreenName());
			System.out.println("用户的地址是："+user.getLocation());
			System.out.println("用户的描述是："+user.getDescription());
		}
	}
	
	public void printStringArray(String[]uids){
		if(uids==null||uids.length<=0)
		{
			System.out.println("获取用户信息记录为空，请检查");
			return;
		}
		System.out.println("--------------------------------------------");
		System.out.println("获取用户信息记录数目是： "+uids.length);
		for(int i=0;i<2;i++){
			System.out.println("第num:"+i+"条记录");
			testUserById(uids[i]);
		}
	}
}
