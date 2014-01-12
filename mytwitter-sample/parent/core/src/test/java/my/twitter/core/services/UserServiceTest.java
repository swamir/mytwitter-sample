package my.twitter.core.services;

import javax.annotation.Resource;

import my.twitter.core.domain.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/testApplicationContext.xml"})
public class UserServiceTest {
	@Resource
	private UserService userService;
	
	@Test
	public void testGetNewUser() {
		String userName = "testUser";
		User user = userService.getUser(userName);
		assertNotNull(user);
		assertEquals(user.getUserName(), userName);
		String userName2 = "testUser2";
		User user2 = userService.getUser(userName2);
		assertNotNull(user2);
		assertEquals(user2.getUserName(), userName2);
		assertNotEquals(user, user2);
	}
	
	@Test
	public void testGetExistingUser() {
		String userName = "testUser";
		User user = userService.getUser(userName);
		assertNotNull(user);
		assertEquals(userName, user.getUserName());
		User user2 = userService.getUser(userName);
		assertEquals(user, user2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetInvalidUser() {
		String userName = null;
		userService.getUser(userName);
		assertFalse(true);
	}
	
	@Test
	public void testAddValidFollower() {
		User user1 = userService.getUser("user1");
		User userToFollow = userService.getUser("userToFollow");
		assertEquals(0, user1.getFollows().size());
		userService.addFollowing(user1, userToFollow);
		assertEquals(1, user1.getFollows().size());
		assertEquals(userToFollow, user1.getFollows().iterator().next());		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddAlreadyFollowing() {
		User user1 = userService.getUser("user1");
		User userToFollow = userService.getUser("userToFollow");
		userService.addFollowing(user1, userToFollow);
		userService.addFollowing(user1, userToFollow);
		assertFalse(true);
	}
}
