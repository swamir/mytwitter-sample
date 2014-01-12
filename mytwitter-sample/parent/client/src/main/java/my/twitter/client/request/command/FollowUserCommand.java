package my.twitter.client.request.command;

import javax.ws.rs.core.Form;

import org.apache.cxf.jaxrs.client.WebClient;

public class FollowUserCommand extends BaseRequestCommand {
	static final String PATH = "/followUser";

	private String userName;
	private String followUserName;

	public FollowUserCommand(String userName, String followUserName) {
		this.userName = userName;
		this.followUserName = followUserName;
		client = this.getWebClient(PATH);
		client.type("application/x-www-form-urlencoded"); 
	}
	
	Form getForm() {
		Form form = new Form();
		form.param("userName", this.userName);
		form.param("followUserName", this.followUserName);
		return form;
	}

	public void execute() {
		client.post(getForm());
	}

	public String getUserName() {
		return userName;
	}

	public String getFollowUserName() {
		return followUserName;
	}

}
