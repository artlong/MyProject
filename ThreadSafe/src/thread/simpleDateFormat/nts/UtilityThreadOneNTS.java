package thread.simpleDateFormat.nts;

import java.util.Date;

import org.apache.log4j.Logger;

public class UtilityThreadOneNTS extends Thread {
	Logger logger = Logger.getLogger(UtilityThreadOneNTS.class);
    private String myName;
    private String strDate;
    private int pt = 1;
    
    public UtilityThreadOneNTS(String myName, String strDate, int p) {
    	this.myName = myName;
    	this.strDate = strDate;
        this.pt = p;   
    }   
    
    public void run(){
    	logger.info("    " + myName + " BEGIN(" + pt + "), STR_DATE(" + strDate + ")");
        for(int i=0; i <pt; i++) {
        	try {
				Date endDate = Utility.toDateObj(strDate, "yyyy/MM/dd");
				int o1Int = Integer.parseInt(Utility.toDateStr(endDate, "yyyyMMdd"));
        	} catch (Exception e) {
        		logger.error("    " + myName + " BEGIN(" + pt + "), STR_DATE(" + strDate + ") No:[" + (i+1) + "] Error");
        		logger.error(e, e);
        	}
        }
    	logger.info("    " + myName + " END !!!");
    }

}
