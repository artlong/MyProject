package two;

import org.apache.log4j.Logger;


import service.TempService;

public class TwoService {
	Logger logger= Logger.getLogger("service");
	
	public void sayHi() {
		new TempService().sayHi();
		logger.info("SerivceLayer who sayHi ?");
		
		new TwoDao().sayHi();
	}
}
