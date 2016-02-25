import one.OneService;

import org.apache.log4j.Logger;

import two.TwoService;


public class PlayOne {
	
	private static Logger logger = Logger.getLogger(PlayOne.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("say Hi!");

		new OneService().sayHi();	// logger use getClass(), log will show all class
		logger.info("================    ================");
		new TwoService().sayHi();	// logger use special string, log will only show the string ==> 查log時,資訊不全

	}

}
