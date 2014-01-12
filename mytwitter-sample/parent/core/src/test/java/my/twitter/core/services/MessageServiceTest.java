package my.twitter.core.services;

import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import my.twitter.core.domain.PostMessage;
import my.twitter.core.domain.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testApplicationContext.xml"})
public class MessageServiceTest {
	@Resource
	private MessageService messageService;
	
	private User user;
	
	@Before
	public void init() {
		user = new User("userName");
	}
	
	@Test
	public void testPostMessage() {
		PostMessage msg = new PostMessage(user, "msg1");
		messageService.postMessage(msg);
		assertEquals(1, user.getMessages().size());
		assertEquals("msg1", user.getMessages().iterator().next().getMessage());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidPostMessage() {
		messageService.postMessage(null);
		assertFalse(true);
	}
	
	@Test
	public void testGetWallMessagesWithoutFollows() {
		PostMessage msg = new PostMessage(user, "msg1");
		messageService.postMessage(msg);
		Set<PostMessage> msgs = messageService.getWallMessages(user);
		assertEquals(1, msgs.size());
		assertEquals("msg1", msgs.iterator().next().getMessage());
	}
	
	@Test
	public void testGetWallMessageWithNoMessages() {
		Set<PostMessage> msgs = messageService.getWallMessages(user);
		assertEquals(0, msgs.size());
	}
	
	@Test
	public void testGetWallMessagesWithFollows() {
		User user2 = new User("user 2");
		user.getFollows().add(user2);
		PostMessage user2msg = new PostMessage(user2, " user 2 post ");
		messageService.postMessage(user2msg);
		PostMessage msg = new PostMessage(user, "msg1");
		messageService.postMessage(msg);
		Set<PostMessage> wallMsgs = messageService.getWallMessages(user);
		assertEquals(2, wallMsgs.size());
	}
	
	@Test
	public void testGetWallMessagesOrder() throws Exception {
		User user3 = new User("user 3");
		user.getFollows().add(user3);
		PostMessage user3msg = new PostMessage(user3, "user 3 post");
		messageService.postMessage(user3msg);
		Thread.sleep(10);
		User user2 = new User("user 2");
		user.getFollows().add(user2);
		PostMessage user2msg = new PostMessage(user2, "user 2 post");
		messageService.postMessage(user2msg);
		Thread.sleep(10);
		PostMessage msg = new PostMessage(user, "msg1");
		messageService.postMessage(msg);
		Set<PostMessage> wallMsgs = messageService.getWallMessages(user);
		assertEquals(3, wallMsgs.size());
		Iterator<PostMessage> msgIter = wallMsgs.iterator();
		assertEquals("user 3 post", msgIter.next().getMessage());
		assertEquals("user 2 post", msgIter.next().getMessage());
		assertEquals("msg1", msgIter.next().getMessage());
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetWallMessageWithInvalidInput() {
		messageService.getWallMessages(null);
		assertFalse(true);
	}
}
