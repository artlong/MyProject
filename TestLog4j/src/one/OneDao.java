package one;

import org.apache.log4j.Logger;

public class OneDao {
	Logger logger= Logger.getLogger(getClass());
	
	public void sayHi() {
		logger.info("DaoLayer who sya Hi ?");
	}
}
