package vo;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import util.JettUtils;

public class Hello {
	private static Logger logger = Logger.getLogger(Hello.class);
	
	public static void main(String[] args) {
		try {
			String input = "試試看";
			String encoding = "BIG5";	// UTF-8, MS950, BIG5
			int length = 4;				// UTF-8 > 6=2, MS950,BIG5 > 6=3
			
//			if(true) encoding = "UTF-8";
			
			String encodingInput = JettUtils.encodingString(input, length, encoding);
			logger.info(input + " >> [" + encodingInput + "]");
			logger.info(input + " >> [" + StringUtils.leftPad(encodingInput, 5, "A") + "]");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
