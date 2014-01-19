package my.twitter.client.request.command;

import java.util.Set;
import java.util.TreeSet;

import javax.ws.rs.core.GenericType;

import my.twitter.core.domain.PostMessage;
import my.twitter.core.services.TimeComparator;

public class UserMessageCommand extends BaseRequestCommand {
	static final String PATH = "/getMessage";

	private String userName;

	public UserMessageCommand(String userName) {
		this.userName = userName;
		client = this.getWebClient(PATH);
	}

	public void execute() {
		client.query("userName", userName);
		GenericType<Set<PostMessage>> genericResponseType = new GenericType<Set<PostMessage>>(){};
		TreeSet<PostMessage> messages = new TreeSet<PostMessage>(new TimeComparator());
		messages.addAll(client.get(genericResponseType));
		for(PostMessage message: messages) {
			System.out.println(message);
		}
	}

	public String getUserName() {
		return userName;
	}

}
