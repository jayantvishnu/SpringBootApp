/**
 * 
 */
package employee.restapi.app.response.vo;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import employee.restapi.app.vo.Employee;

/**
 * @author JayantV
 *
 */
@JsonInclude(Include.NON_NULL)
public class EmployeeResponse extends Response {
	
	private List<Employee> employee;
	
	private Map<String,String> invalidFields; 
	
	public Map<String, String> getInvalidFields() {
		return invalidFields;
	}

	public void setInvalidFields(Map<String, String> invalidFields) {
		this.invalidFields = invalidFields;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

}
