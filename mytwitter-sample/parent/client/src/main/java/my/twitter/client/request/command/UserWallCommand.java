package my.twitter.client.request.command;

import java.util.Set;

import javax.ws.rs.core.GenericType;

import my.twitter.core.domain.PostMessage;

public class UserWallCommand extends BaseRequestCommand {
	static final String PATH = "/getWallMessage";

	private String userName;

	public UserWallCommand(String userName) {
		this.userName = userName;
		client = this.getWebClient(PATH);
	}

	public void execute() {
		client.query("userName", userName);
		GenericType<Set<PostMessage>> genericResponseType = new GenericType<Set<PostMessage>>(){};		
		Set<PostMessage> messages = client.get(genericResponseType);
		for(PostMessage message: messages) {
			System.out.println(message);
		}
	}

	public String getUserName() {
		return userName;
	}
	
}
