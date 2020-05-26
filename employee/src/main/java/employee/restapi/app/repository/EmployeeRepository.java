/**
 * 
 */
package employee.restapi.app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import employee.restapi.app.vo.Employee;

/**
 * @author JayantV
 *
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String>{

	Optional<Employee> findById(Integer id);

	Integer deleteById(Integer id);

}
