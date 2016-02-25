package thread.simpleDateFormat.nts;

import org.apache.log4j.Logger;

import thread.simpleDateFormat.nts.UtilityThreadOneNTS;
import thread.simpleDateFormat.nts.UtilityThreadTwoNTS;


public class TestUtilityNTS {
	public static void main(String[] args) {
		// 兩個數字愈大, 出錯的機率愈高  [但不一定會出錯, 因為是 Thread, 誰跑誰休息沒一定]
		int oneTimes = 30000;  // 第一種 Thread 跑的次數   [將日期轉為數字(模擬 DialoutDetailQueryAction 做排序的動作)]
		int twoTimes = 200;    // 第二種 Thread 跑的次數   [將日期轉為民國年 (模擬 DialoutDetailQueryAction 做排序時, 其它Thread呼叫]
		try {
			Logger logger = Logger.getLogger(TestUtilityNTS.class);
			logger.info(" *** Test UtilityNTS beg *** ");
			Thread thread1 = new UtilityThreadOneNTS("AAAAA", "2011/03/09", oneTimes);
			Thread thread2 = new UtilityThreadTwoNTS("BBBBB", "2011/04/09", twoTimes);
			Thread thread3 = new UtilityThreadOneNTS("CCCCC", "2011/05/09", oneTimes);
			thread1.start();
			thread2.start();
			thread3.start();
			thread1.join();
			thread2.join();
			thread3.join();
			logger.info(" *** Test UtilityNTS end *** ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
