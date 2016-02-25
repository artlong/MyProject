package two;

import org.apache.log4j.Logger;

public class TwoDao {
	Logger logger= Logger.getLogger("dao");
	
	public void sayHi() {
		logger.info("DaoLayer who say Hi ?");
	}
}
