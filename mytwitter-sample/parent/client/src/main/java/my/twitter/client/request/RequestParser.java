package my.twitter.client.request;

import my.twitter.client.request.command.FollowUserCommand;
import my.twitter.client.request.command.PostMessageCommand;
import my.twitter.client.request.command.RequestCommand;
import my.twitter.client.request.command.UserMessageCommand;
import my.twitter.client.request.command.UserWallCommand;

import org.apache.cxf.common.util.StringUtils;

public class RequestParser {
	private static final String POST_TOKEN = "->",
							 FOLLOWS_TOKEN = " follows ",
							    WALL_TOKEN = " wall";
	public RequestCommand parse(String request) {
		RequestCommand command = null;
		if(StringUtils.isEmpty(request)) {
			throw new IllegalArgumentException();
		}
		int idx = 0;
		if((idx = request.indexOf(POST_TOKEN)) != -1) {
			String userName = request.substring(0, idx);
			String message  = request.substring(idx+POST_TOKEN.length());
			command = new PostMessageCommand(userName, message);
		}
		else if((idx = request.indexOf(FOLLOWS_TOKEN))!= -1) {
			String userName = request.substring(0, idx);
			String followUserName  = request.substring(idx+FOLLOWS_TOKEN.length());
			command = new FollowUserCommand(userName, followUserName);
		}
		else if((idx = request.indexOf(WALL_TOKEN))!= -1) {
			String userName = request.substring(0, idx);
			command = new UserWallCommand(userName);
		}
		else {
			command = new UserMessageCommand(request);
		}
		return command;
	}
}
