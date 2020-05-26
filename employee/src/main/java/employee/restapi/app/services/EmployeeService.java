/**
 * 
 */
package employee.restapi.app.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import employee.restapi.app.repository.EmployeeRepository;
import employee.restapi.app.vo.Employee;

/**
 * @author JayantV
 *
 */
@Service
public class EmployeeService {
	
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
		public List<Employee> getAllEmployees(String filter){
			List<Employee> employees = new ArrayList<>();
			try {
				this.employeeRepository.findAll().forEach(employees::add);
			}
			catch(Exception e) {
				e.printStackTrace();
				return employees;
			}
			if(null==filter || filter.isEmpty()) {
				return employees;
			}
			/**
			 * by default ordering is on the basis of Id
			 **/
			if(filter.equalsIgnoreCase("byName")) {
				employees = employees.stream()
									 .sorted(Comparator.comparing(Employee::getName))
									 .collect(Collectors.toList());
			}
			else if(filter.equalsIgnoreCase("byDept")) {
				employees = employees.stream()
									 .sorted(Comparator.comparing(Employee::getDepartment))
									 .collect(Collectors.toList());
			}
			else if(filter.equalsIgnoreCase("reverse")) {
				employees = employees.stream()
									 .sorted(Comparator.comparing(Employee::getId).reversed())
									 .collect(Collectors.toList());
			}
			return employees;
		}
		
		public Optional<Employee> getEmployeeById(Integer id) {
			try {
				return this.employeeRepository.findById(id);
			}
			catch(Exception e) {
				e.printStackTrace();
				return Optional.empty();
			}
		}
		
		public Employee saveEmployee(Employee employee) {
			try {
				return this.employeeRepository.save(employee);
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public Employee updateEmployee(Employee employee) {
			try {
				return this.employeeRepository.save(employee);
			}
			catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		@Transactional
		public int deleteEmployee(Integer id) {
			int count=0;
			try {
				count = this.employeeRepository.deleteById(id);	
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return count;
		}
}
