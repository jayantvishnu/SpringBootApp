package employee.restapi.app.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity
public class Employee {

	@Id
	@NotNull ()
	private Integer id;
	@NotNull (message = "name should not be null")
	@NotEmpty (message = "name should not be empty")
	private String name;
	@NotNull (message = "email should not be null")
	@NotEmpty (message = "email should not be empty")
	private String email;
	@NotNull (message = "mobileNo should not be null")
	@NotEmpty (message = "mobileNo should not be empty")
	@Size(min=10,max=10,message = "mobileNo should be of 10 digit")
	private String mobileNo;
	@NotNull (message = "department should not be null")
	@NotEmpty (message = "department should not be empty")
	private String department;

	public Employee(Integer id, String name, String email, String mobile, String dept) {
		this.name = name;
		this.id=id;
		this.email=email;
		this.mobileNo=mobile;
		this.department=dept;
	}

	public Employee() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", email=" + email + ", mobileNo=" + mobileNo + ", department="
				+ department;
	}
	
}
