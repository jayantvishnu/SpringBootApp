package employee.restapi.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import employee.restapi.app.Exceptions.DataNotFoundException;
import employee.restapi.app.Exceptions.DataOperationException;
import employee.restapi.app.Exceptions.EmployeeNotFound;
import employee.restapi.app.services.EmployeeService;
import employee.restapi.app.vo.Employee;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	private String erroeMSg = "Employee data not found for Id: ";

	@GetMapping(path = "employee")
	public List<Employee> getAllEmployee(@RequestParam(value="filter",required=false) String filter) throws DataNotFoundException, DataOperationException {

		List <Employee> employees = this.employeeService.getAllEmployees(filter);
		
		if(null == employees) {
			throw new DataOperationException("Unable to retrive data of Employees,try again");
		}
		
		if (!employees.isEmpty()) {
			return employees;
		} else {
			throw new DataNotFoundException("No Data found");
		}
	}

	@GetMapping(path = "employee/{id}")
	public Employee getEmployeeById(@PathVariable("id") Integer id) throws EmployeeNotFound, DataOperationException {
		
		Optional<Employee> emp = this.employeeService.getEmployeeById(id);
		 
		if (emp.isPresent()) {
			if(null!=emp.get()) {
				return emp.get();
			}
			else {
				throw new DataOperationException("Unable to retrive data of employee for Id: " +id+ " ,try again");
			}
		} else {
			throw new EmployeeNotFound(erroeMSg+id);
		}
	}

	@PostMapping(path = "employee")
	public Employee saveEmployee(@Valid @RequestBody Employee employee) throws DataOperationException {
		
			Employee emp = this.employeeService.saveEmployee(employee);
			
			if(null!=emp)
				return emp;
			else 
				throw new DataOperationException("Employee data saving failed,try again");
	}

	@PutMapping(path = "employee")
	public Employee updateEmployee(@Valid @RequestBody Employee employee) throws EmployeeNotFound, DataOperationException {
			int id = employee.getId();
			
			if(this.employeeService.getEmployeeById(id).isPresent()) {
				Employee emp = this.employeeService.updateEmployee(employee);
				if(null!=emp)
					return emp;
				else
					throw new DataOperationException("Employee data updation failed,try again");
			}
			else {
				throw new EmployeeNotFound(erroeMSg+id);
			}
	}

	@DeleteMapping(path = "employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) throws EmployeeNotFound, DataOperationException {
		
		if (this.employeeService.getEmployeeById(id).isPresent()) {
			int result = this.employeeService.deleteEmployee(id);
			if(result>0)
				return new ResponseEntity<>("Success",HttpStatus.OK);
			else
				throw new DataOperationException("Employee deleteion failed,try again");
		}
		else {
			throw new EmployeeNotFound(erroeMSg+id);
		}
	}
}
