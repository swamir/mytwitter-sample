package my.twitter.core.services;


import static org.junit.Assert.assertEquals;

import my.twitter.core.domain.PostMessage;
import my.twitter.core.domain.User;

import org.junit.Before;
import org.junit.Test;

public class TimeComparatorTest {
	private User user;
	private TimeComparator timeComparator;
	
	@Before
	public void init() {
		user = new User("testUser");
		timeComparator = new TimeComparator();
	}
	
	@Test
	public void testSorts() throws Exception{
		PostMessage msg1 = new PostMessage(user, "message 1");
		Thread.sleep(2);
		PostMessage msg2 = new PostMessage(user, "message 2");
		Thread.sleep(2);
		PostMessage msg3 = new PostMessage(user, "message 3");
		assertEquals(timeComparator.compare(msg1, msg2),-1);
		assertEquals(timeComparator.compare(msg2, msg3),-1);
		assertEquals(timeComparator.compare(msg2, msg1),1);
		assertEquals(timeComparator.compare(msg3, msg2),1);
		assertEquals(timeComparator.compare(msg3, msg3),0);
	}
	
	@Test
	public void testInvalidData() {
		PostMessage msg1 = new PostMessage(user, "message 1");
		assertEquals(timeComparator.compare(msg1, null),0);
		assertEquals(timeComparator.compare(null, msg1),0);
	}
}
