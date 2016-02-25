import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jett.transform.ExcelTransformer;

import org.apache.log4j.Logger;

import util.JettUtils;
import vo.Employee;


public class HelloWorld {
	private static Logger logger = Logger.getLogger(HelloWorld.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String template = JettUtils.getXls("template-D.xls");	// template-A.xls, template-B.xls, template-C.xls
		String result = JettUtils.getXls("z-Emps.xls");
		try {
			ExcelTransformer transformer = new ExcelTransformer();
			Map<String, Object> beans = new HashMap<String, Object>();
			
			beans.put("employees", getEmployees());
//			transformer.addFixedSizeCollectionName("employees");
			transformer.transform(template, result, beans);
			logger.info("HelloWorld done!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public static List<Employee> getEmployees() {
		List<Employee> listEmployee = new ArrayList<Employee>();
		Employee a = new Employee();
		a.setFirstName("Robert");
		a.setLastName("Stack");
		a.setSalary(100000);
		listEmployee.add(a);
		
		Employee b = new Employee();
		b.setFirstName("Suzie");
		b.setLastName("Queue");
		b.setSalary(9999);
		b.setManager(a);
		listEmployee.add(b);
		
		return listEmployee;
	}
}
