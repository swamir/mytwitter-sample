package my.twitter.client.request.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.Set;
import java.util.TreeSet;

import javax.ws.rs.core.GenericType;

import my.twitter.core.domain.PostMessage;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import org.mockito.Mockito;

public class UserWallCommandTest {
	@Test 
	public void test() {
		UserWallCommand command = new UserWallCommand("testUser");
		GenericType<Set<PostMessage>> genericResponseType = new GenericType<Set<PostMessage>>(){};		
		WebClient client = command.getWebClient(UserWallCommand.PATH);
		client = Mockito.spy(client);
		doReturn(new TreeSet<PostMessage>()).when(client).get(genericResponseType);
		command.setClient(client);
		command.execute();
		assertEquals(client.getCurrentURI().toString(), command.getBaseUrl() + UserWallCommand.PATH + "?userName=testUser");
	}

}
