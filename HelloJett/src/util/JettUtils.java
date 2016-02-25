package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.Employee;

public class JettUtils {
	public static String getXls(String xlsName) {
		return "./xls/" + xlsName;
	}
	
	public static Map<String, Object> genBeanList() {
		List<Map<String, Object>> beansList = new ArrayList<Map<String, Object>>();
		Map<String, Object> beans = new HashMap<String, Object>();
		Employee a = new Employee();
		a.setFirstName("Hello");
		beans.put("a", a);
		a = new Employee();
		a.setFirstName("World");
		beans.put("b", a);
		beans.put("c", "堃");
		
		return beans;
	}
	
	public static Map<String, Object> genSimpleBean() {
		Map<String, Object> beans = new HashMap<String, Object>();
		Employee a = new Employee();
		a.setFirstName("Hello");
		beans.put("a", a);
		a = new Employee();
		a.setFirstName("World");
		beans.put("b", a);
		beans.put("c", "堃");
		
		return beans;
	}
	
	public static String encodingString(String input, int leng, String encoding) throws Exception {
		if(input == null) return null;
		return new String(JettUtils.subarray(input.getBytes(encoding), 0, leng), encoding);
	}

	public static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive) {
		if(array == null) { return null; }
		if(startIndexInclusive < 0) {
			startIndexInclusive = 0;
		}
		
		if(endIndexExclusive > array.length) {
			endIndexExclusive = array.length;
		}
		
		int newSize = endIndexExclusive - startIndexInclusive;
		if(newSize <= 0) { return new byte[0]; }
		byte[] subarray = new byte[newSize];
		System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
		return subarray;
	}

}
