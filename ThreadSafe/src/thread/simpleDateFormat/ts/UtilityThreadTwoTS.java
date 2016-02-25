package thread.simpleDateFormat.ts;

import org.apache.log4j.Logger;

public class UtilityThreadTwoTS extends Thread {
	Logger logger = Logger.getLogger(UtilityThreadOneTS.class);
    private String myName;
    private String strDate;
    private int pt = 1;
    
    public UtilityThreadTwoTS(String myName, String strDate, int p) {
    	this.myName = myName;
    	this.strDate = strDate;
        this.pt = p;   
    }   
    
    public void run(){
    	logger.info("    " + myName + " BEGIN(" + pt + "), STR_DATE(" + strDate + ")");
        for(int i=0; i <pt; i++) {
        	UtilityTS.toLocalDateStr(UtilityTS.toDateObj(strDate, "yyyy/MM/dd"));
        }
    	logger.info("    " + myName + " END !!!");
    }

}
