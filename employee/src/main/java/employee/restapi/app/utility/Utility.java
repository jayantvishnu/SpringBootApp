package employee.restapi.app.utility;

import java.util.HashMap;
import java.util.Map;

import employee.restapi.app.vo.Employee;

public class Utility {
	
	public static boolean isNullOrEmpty(String s) {
		if(null == s || s.isEmpty() || s.trim().length()==0)
			return true;
		return false;
	}

	public static Map<String, String> validateFields(Employee employee) {
		Map <String,String> map = new HashMap<>();
		
		
		/*
		 * // if(isNullOrEmpty(employee.getId())) { // map.put("id", "Inavlid"); } //
		 */		if(isNullOrEmpty(employee.getName())) { 
			map.put("name", "Inavlid"); 
		} 
		if(isNullOrEmpty(employee.getEmail())) {
			map.put("email", "Inavlid");
		}
		if(isNullOrEmpty(employee.getMobileNo())) {
				map.put("mobileNo", "Inavlid");
		}
		else if((employee.getMobileNo().length()>10 && employee.getMobileNo().length()<10)){
				map.put("mobileNo", "Inavlid");
		}
		if(isNullOrEmpty(employee.getDepartment())) {
			map.put("department", "Inavlid");
		}
		return map;
	}
}
