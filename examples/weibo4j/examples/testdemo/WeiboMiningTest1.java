package weibo4j.examples.testdemo;

import java.util.Scanner;

import weibo4j.Users;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

public class WeiboMiningTest1 {
	public static void main(String[] args) {
		System.out.println("----输入access_token-----");
		String access_token = new Scanner(System.in).nextLine();
		String uid = WeiboConstant.UID;
		Users um = new Users(access_token);
		//
		//String url = "https://api.weibo.com/2/friendships/friends/in_common.json?";
		BareBonesBrowserLaunch.openURL("");
		try {
			User user = um.showUserById(uid);
			Log.logInfo(user.toString());
			System.out.println(user.getName());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}
}
