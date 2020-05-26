/**
 * 
 */
package restapi.app.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import employee.restapi.app.controller.EmployeeController;
import employee.restapi.app.services.EmployeeService;
import employee.restapi.app.vo.Employee;

/**
 * @author JayantV
 *
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = EmployeeController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;

	@Mock
	EmployeeService employeeService;

	@Autowired
	MockMvc mockMvc;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}


	@Test
	public void getAllEmployeesTest() throws Exception {
		List<Employee> employees = Arrays.asList(new Employee(1, "User1", "user1@email.com", "9876543210", "HR"),
												 new Employee(2, "User2", "user2@email.com", "9876543210", "IT"),
												 new Employee(3, "User3", "user3@email.com", "9876543210", "Admin"),
												 new Employee(4, "User4", "user4@email.com", "9876543210", "Admin"),
												 new Employee(5, "User5", "user5@email.com", "9876543210", "HR"));

		when(employeeService.getAllEmployees(null)).thenReturn(employees);

		employeeService.getAllEmployees("byName");

		mockMvc.perform(get("/employee")).andExpect(content().contentType(MediaType.APPLICATION_JSON))
										 .andExpect(jsonPath("$[0].id").value(1))
										 .andExpect(jsonPath("$[0].name").value("User1"))
										 .andExpect(jsonPath("$[1].id").value(2))
										 .andExpect(jsonPath("$[1].name").value("User2"));

		verify(employeeService, times(1)).getAllEmployees(Mockito.anyString());
	}

	@Test
	public void getEmployeeByIdTest() throws Exception {

		Optional<Employee> emp = Optional.ofNullable(new Employee(1, "User1", "user1@email.com", "9876543210", "HR"));

		when(employeeService.getEmployeeById(1)).thenReturn(emp);

		mockMvc.perform(get("/employee/1")).andExpect(content().contentType(MediaType.APPLICATION_JSON))
										   .andExpect(jsonPath("id").value(1))
										   .andExpect(jsonPath("name").value("User1"));

		verify(employeeService, times(1)).getEmployeeById(1);
	}

	@Test
	public void saveEmployeeAPI() throws Exception {

		Employee mockEmployee = new Employee(1, "User1", "user1@email.com", "9876543210", "HR");

		Mockito.when(employeeService.saveEmployee(Mockito.any(Employee.class))).thenReturn(mockEmployee);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employee")
															  .content(asJsonString(mockEmployee))
															  .contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		Gson g = new Gson();
		Employee e = g.fromJson(result.getResponse().getContentAsString(), Employee.class);
		assertEquals("1", e.getId().toString());

	}

	@Test
	public void deleteEmployeeTest() throws Exception {

		Optional<Employee> emp = Optional.ofNullable(new Employee(1, "User1", "user1@email.com", "9876543210", "HR"));
		when(employeeService.getEmployeeById(1)).thenReturn(emp);

		when(employeeService.deleteEmployee(1)).thenReturn(1);
		MvcResult result = mockMvc.perform(delete("/employee/{id}", 1)).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("Success", result.getResponse().getContentAsString());
		verify(employeeService, times(1)).getEmployeeById(1);
	}

	@Test
	public void updateEmployeeTest() throws Exception {
		Employee emp1 = new Employee(1, "User11", "user1@email.com", "9876543210", "HR");

		Optional<Employee> emp = Optional.ofNullable(new Employee(1, "User1", "user1@email.com", "9876543210", "HR"));
		when(employeeService.getEmployeeById(1)).thenReturn(emp);
		
		Mockito.when(employeeService.updateEmployee(Mockito.any(Employee.class))).thenReturn(emp1);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/employee").content(asJsonString(emp1))
															  .contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		Gson g = new Gson();
		Employee e = g.fromJson(result.getResponse().getContentAsString(), Employee.class);
		assertEquals("User11", e.getName());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
