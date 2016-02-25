package thread.simpleDateFormat.ts;

import org.apache.log4j.Logger;

import thread.simpleDateFormat.ts.UtilityThreadOneTS;
import thread.simpleDateFormat.ts.UtilityThreadTwoTS;


public class TestUtilityTS {
	public static void main(String[] args) {
		// 兩個數字調的再大, 也不會出錯(我有跑了 300,000 次)
		int oneTimes = 30000;  // 第一種 Thread 跑的次數   [將日期轉為數字(模擬 DialoutDetailQueryAction 做排序的動作)]
		int twoTimes = 30000;  // 第二種 Thread 跑的次數   [將日期轉為民國年 (模擬 DialoutDetailQueryAction 做排序時, 其它Thread呼叫]
		try {
			Logger logger = Logger.getLogger(TestUtilityTS.class);
			logger.info(" *** Test UtilityTS beg *** ");

			Thread thread1 = new UtilityThreadOneTS("AAAAA", "2011/03/09", oneTimes);
			Thread thread2 = new UtilityThreadTwoTS("BBBBB", "2011/04/09", twoTimes);
			Thread thread3 = new UtilityThreadOneTS("CCCCC", "2011/05/09", oneTimes);
			thread1.start();
			thread2.start();
			thread3.start();
			thread1.join();
			thread2.join();
			thread3.join();
			logger.info(" *** Test UtilityTS end *** ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
