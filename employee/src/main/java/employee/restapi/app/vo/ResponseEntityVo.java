/**
 * 
 */
package employee.restapi.app.vo;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import employee.restapi.app.Exceptions.DataNotFoundException;
import employee.restapi.app.Exceptions.DataOperationException;
import employee.restapi.app.Exceptions.EmployeeNotFound;

/**
 * @author JayantV
 *
 */
@ControllerAdvice
public class ResponseEntityVo {
	
	@ExceptionHandler(EmployeeNotFound.class)
	public final ResponseEntity<ErrorEntity> handleEmployeeNotFoundException(EmployeeNotFound e) {
		ErrorEntity err = new ErrorEntity(new Date(),e.getMessage(),HttpStatus.NOT_FOUND.value(),null,HttpStatus.NOT_FOUND);
		return new ResponseEntity<ErrorEntity>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataNotFoundException.class)
	public final ResponseEntity<ErrorEntity> handleDataNOtFoundException(DataNotFoundException e){
		ErrorEntity err = new ErrorEntity(new Date(),e.getMessage(),HttpStatus.NOT_FOUND.value(),null,HttpStatus.NOT_FOUND);
		return new ResponseEntity<ErrorEntity>(err,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(DataOperationException.class)
	public final ResponseEntity<ErrorEntity> handleDataSavingException(DataOperationException e){
		ErrorEntity err = new ErrorEntity(new Date(),e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(),null,HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<ErrorEntity>(err,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
