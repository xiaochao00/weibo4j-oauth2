package weibo4j.examples.testdemo;


import weibo4j.http.HttpClient;
import weibo4j.http.Response;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

public class HttpClientTest1 {
	public static void main(String []args) throws WeiboException{
		String url = WeiboConfig.getValue("authorizeURL").trim() + "?client_id="
		+ WeiboConfig.getValue("client_ID").trim() + "&redirect_uri="
		+ WeiboConfig.getValue("redirect_URI").trim()
		+ "&response_type=" + "code";
		System.out.println(url);
//		HttpClient hc = new HttpClient();
//		Response res = hc.get(url, "");
//		System.out.println("-------------");
//		System.out.println(res.getResponseAsString());
//		System.out.println("============");
//		System.out.println(res.getResponseHeader("code"));
//		System.out.println("-----------");
	}
}
