/**
 * 
 */
package employee.restapi.app.response.vo;

import org.springframework.http.HttpStatus;

/**
 * @author JayantV
 *
 */
public class Response {
	
	private HttpStatus responseCode;
	private String responseMsg;
	
	public HttpStatus getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(HttpStatus status) {
		this.responseCode = status;
	}
	public String getResponseMsg() {
		return responseMsg;
	}
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
}
