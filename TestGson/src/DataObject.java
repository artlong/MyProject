import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DataObject {
	private int data1 = 100;
	private String data2 = "hello";
	private List<String> list = new ArrayList<String>() {
	  {
		add("String 1");
		add("String 2");
		add("String 3");
	  }
	};
	private List<String> roleFunc = null;
 
	//getter and setter methods
	
	public List<String> getRoleFunc() {
		return roleFunc;
	}

	public void setRoleFunc(List<String> roleFunc) {
		this.roleFunc = roleFunc;
	}

	public DataObject() {
		Set<String> roleFunc = new HashSet<String>();
		for(String obj:list) {
			roleFunc.add(obj);
		}
		List aa = new ArrayList();
		aa.addAll(roleFunc);
		setRoleFunc(aa);
	}
 
	@Override
	public String toString() {
	   return "DataObject [data1=" + data1 + ", data2=" + data2 + ", list=" + list + ", roleFunc" + roleFunc + "]";
	}
}
