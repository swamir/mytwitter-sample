package my.twitter.client.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import my.twitter.client.request.command.FollowUserCommand;
import my.twitter.client.request.command.PostMessageCommand;
import my.twitter.client.request.command.RequestCommand;
import my.twitter.client.request.command.UserMessageCommand;
import my.twitter.client.request.command.UserWallCommand;

import org.junit.Before;
import org.junit.Test;

public class RequestParserTest {
	private RequestParser parser;
	@Before
	public void init() {
		parser = new RequestParser();
	}
	@Test
	public void testPostMessageStr() {
		String str = "Alice -> I love the weather today";
		RequestCommand command = parser.parse(str);
		assertTrue(command instanceof PostMessageCommand);
		PostMessageCommand postCommand = (PostMessageCommand) command;
		assertEquals("Alice ", postCommand.getUserName());
		assertEquals(" I love the weather today", postCommand.getMessage());
	}
	@Test
	public void testAddFollowStr() {
		String str = "Charlie follows Bob";
		RequestCommand command = parser.parse(str);
		assertTrue(command instanceof FollowUserCommand);
		FollowUserCommand followCommand = (FollowUserCommand) command;
		assertEquals("Charlie", followCommand.getUserName());
		assertEquals("Bob", followCommand.getFollowUserName());
	}
	@Test
	public void testUserWallStr() {
		String str = "Charlie wall";
		RequestCommand command = parser.parse(str);
		assertTrue(command instanceof UserWallCommand);
		UserWallCommand userWall = (UserWallCommand) command;
		assertEquals("Charlie", userWall.getUserName());
	}
	@Test
	public void testUserMessageStr() {
		String str = "Alice ";
		RequestCommand command = parser.parse(str);
		assertTrue(command instanceof UserMessageCommand);
		UserMessageCommand userMessageCommand = (UserMessageCommand) command;
		assertEquals("Alice ", userMessageCommand.getUserName());
	}
}
