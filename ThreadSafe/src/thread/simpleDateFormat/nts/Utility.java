package thread.simpleDateFormat.nts;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class Utility {
	private final static Logger log = Logger.getLogger(Utility.class);

	public static SimpleDateFormat dateFormat = new SimpleDateFormat();


	/**
	 * 轉換日期格式--轉字串
	 * 
	 * @author StanChang
	 * @param date
	 * @param pattern
	 *            欲轉換格式 ex. yyyyMMdd
	 * @return String
	 */
	public static String toDateStr(Date date, String pattern) {
		String finalDate = "";
		try {
			if (date != null) {
				dateFormat.applyPattern(pattern);
				finalDate = dateFormat.format(date);
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		return finalDate;
	}

	public static String toLocalDateStr(Date date) {
		String finalDate = "";
		try {
			if (date != null) {
				dateFormat.applyPattern("yyyy");
				int year = Integer.parseInt(dateFormat.format(date)) - 1911;
				dateFormat.applyPattern(" 年 MM 月 dd 日");
				String tmpDateFormat = dateFormat.format(date);
				finalDate = year + tmpDateFormat;
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		return finalDate;
	}

	/**
	 * 轉換日期格式--轉Date物件
	 * 
	 * @author StanChang
	 * @param date
	 * @param pattern
	 * @return Date
	 */
	public static Date toDateObj(String date, String pattern) {
		Date finalDate = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			finalDate = dateFormat.parse(date);
		} catch (Exception e) {
			log.error(e, e);
		}
		return finalDate;
	}
}
