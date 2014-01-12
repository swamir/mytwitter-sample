package my.twitter.client.request.command;

import javax.ws.rs.core.Form;

public class PostMessageCommand extends BaseRequestCommand {
	static final String PATH = "/postMessage";
	
	private String userName;
	private String message;
	
	public PostMessageCommand(String userName, String message) {
		this.userName = userName;
		this.message = message;
		client = this.getWebClient(PATH);
		client.type("application/x-www-form-urlencoded"); 
	}
	
	Form getForm() {
		Form form = new Form();
		form.param("userName", this.userName);
		form.param("message", this.message);
		return form;
		
	}

	public void execute() {
		client.post(getForm());
	}

	public String getUserName() {
		return userName;
	}

	public String getMessage() {
		return message;
	}

}
