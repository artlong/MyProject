package one;

import org.apache.log4j.Logger;


import service.TempService;

public class OneService {
	Logger logger= Logger.getLogger(getClass());
	
	public void sayHi() {
		new TempService().sayHi();
		logger.info("SerivceLayer who sayHi ?");

		
		new OneDao().sayHi();
	}
}
