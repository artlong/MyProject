package service;

import org.apache.log4j.Logger;

public class TempService {
	Logger logger= Logger.getLogger(TempService.class);
	
	public void sayHi() {
		logger.info("共用Service sayHi");
	}
}
