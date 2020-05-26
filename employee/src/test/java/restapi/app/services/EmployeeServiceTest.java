/**
 * 
 */
package restapi.app.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import employee.restapi.app.EmployeeAdapter;
import employee.restapi.app.repository.EmployeeRepository;
import employee.restapi.app.services.EmployeeService;
import employee.restapi.app.vo.Employee;

/**
 * @author JayantV
 *
 */
@RunWith(MockitoJUnitRunner.Silent.class)	
@SpringBootTest(classes = EmployeeAdapter.class)
public class EmployeeServiceTest {
	@InjectMocks
	EmployeeService employeeService;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	@Test
	public void getAllEmployeeTest(){
		List <Employee> employees = Arrays.asList(
					new Employee(1,"User1","user1@email.com","9876543210","HR"),
					new Employee(2,"User2","user2@email.com","9876543210","IT"),
					new Employee(3,"User3","user3@email.com","9876543210","Admin"),
					new Employee(4,"User4","user4@email.com","9876543210","Admin"),
					new Employee(5,"User5","user5@email.com","9876543210","HR")
				);
		when(employeeService.getAllEmployees(null)).thenReturn(employees);
		List<Employee> empList = employeeService.getAllEmployees(null);
		assertEquals(5,empList.size());
		verify(employeeRepository, times(1)).findAll();	
	}
	@Test
	public void getAllEmployeeByNameTest(){
		List <Employee> employees = Arrays.asList(
					new Employee(1,"User1","user1@email.com","9876543210","HR"),
					new Employee(2,"User2","user2@email.com","9876543210","IT"),
					new Employee(3,"User3","user3@email.com","9876543210","Admin"),
					new Employee(4,"User4","user4@email.com","9876543210","Admin"),
					new Employee(5,"User5","user5@email.com","9876543210","HR")
				);
		when(employeeService.getAllEmployees("byName")).thenReturn(employees);
		List<Employee> empList = employeeService.getAllEmployees("byName");
		assertEquals(5,empList.size());
		verify(employeeRepository, times(1)).findAll();	
	}
	@Test
	public void getAllEmployeeByDeptTest(){
		List <Employee> employees = Arrays.asList(
					new Employee(1,"User1","user1@email.com","9876543210","HR"),
					new Employee(2,"User2","user2@email.com","9876543210","IT"),
					new Employee(3,"User3","user3@email.com","9876543210","Admin"),
					new Employee(4,"User4","user4@email.com","9876543210","Admin"),
					new Employee(5,"User5","user5@email.com","9876543210","HR")
				);
		when(employeeService.getAllEmployees("byDept")).thenReturn(employees);
		List<Employee> empList = employeeService.getAllEmployees("byDept");
		assertEquals("Admin",empList.get(0).getDepartment());
		verify(employeeRepository, times(1)).findAll();	
	}
	@Test
	public void getAllEmployeeInReverseOrderTest(){
		List <Employee> employees = Arrays.asList(
					new Employee(1,"User1","user1@email.com","9876543210","HR"),
					new Employee(2,"User2","user2@email.com","9876543210","IT"),
					new Employee(3,"User3","user3@email.com","9876543210","Admin"),
					new Employee(4,"User4","user4@email.com","9876543210","Admin"),
					new Employee(5,"User5","user5@email.com","9876543210","HR")
				);
		int id=5;
		when(employeeService.getAllEmployees("reverse")).thenReturn(employees);
		List<Employee> empList = employeeService.getAllEmployees("reverse");
		assertEquals(id,empList.get(0).getId().intValue());
		verify(employeeRepository, times(1)).findAll();	
	}
	@Test
	public void getEmployeeByIdTest() {
		Employee employee = new Employee(1,"User1","user1@email.com","9876543210","HR");
		when(employeeService.getEmployeeById(1)).thenReturn(Optional.of(employee));
		Optional <Employee> e = employeeService.getEmployeeById(1);
		assertEquals("1",String.valueOf(e.get().getId()));
		verify(employeeRepository).findById(1);
	}
	
	@Test
	public void saveEmployeeTest()
	{
		Employee employee = new Employee(1,"User1","user1@email.com","9876543210","HR");
		when(employeeService.saveEmployee(employee)).thenReturn(employee);
		Employee emp = employeeService.saveEmployee(employee);
		assertEquals("1",String.valueOf(emp.getId()));
		verify(employeeRepository).save(emp);
	}
	
	@Test
	public void updateEmployeeTest() {
		Employee employee = new Employee(1,"User1","user123@email.com","9876543210","HR");
		when(employeeService.updateEmployee(employee)).thenReturn(employee);
		Employee emp = employeeService.updateEmployee(employee);
		assertEquals("user123@email.com",emp.getEmail());
		verify(employeeRepository).save(emp);
	}
	
	@Test
	public void deleteEmployeeTest() {
		Employee employee = new Employee(1,"User1","user123@email.com","9876543210","HR");
		when(employeeService.deleteEmployee(employee.getId())).thenReturn(1);
		int flag = employeeService.deleteEmployee(employee.getId());
		assertEquals(1, flag);
		verify(employeeRepository).deleteById(employee.getId());
	}
}
