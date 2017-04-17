package weibo4j.examples.testdemo.test1127.utils;

public class SleepUtils {
	public static void sleep(long l){
		try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
