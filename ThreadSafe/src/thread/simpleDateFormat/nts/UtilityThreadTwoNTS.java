package thread.simpleDateFormat.nts;

import org.apache.log4j.Logger;

public class UtilityThreadTwoNTS extends Thread {
	Logger logger = Logger.getLogger(UtilityThreadOneNTS.class);
    private String myName;
    private String strDate;
    private int pt = 1;
    
    public UtilityThreadTwoNTS(String myName, String strDate, int p) {
    	this.myName = myName;
    	this.strDate = strDate;
        this.pt = p;   
    }   
    
    public void run(){
    	logger.info("    " + myName + " BEGIN(" + pt + "), STR_DATE(" + strDate + ")");
        for(int i=0; i <pt; i++) {
        	Utility.toLocalDateStr(Utility.toDateObj(strDate, "yyyy/MM/dd"));
        }
    	logger.info("    " + myName + " END !!!");
    }

}
