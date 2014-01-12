package my.twitter.client.request.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import org.mockito.Mockito;

public class FollowUserCommandTest {
	@Test 
	public void test() {
		FollowUserCommand command = new FollowUserCommand("testUser","userToFollow");
		WebClient client = command.getWebClient(FollowUserCommand.PATH);
		client = Mockito.spy(client);
		Response mock = mock(Response.class);
		doReturn(mock).when(client).post(any());
		command.setClient(client);
		command.execute();
		assertEquals(client.getCurrentURI().toString(), command.getBaseUrl() + FollowUserCommand.PATH);
		assertEquals(command.getForm().asMap().get("userName").get(0), "testUser");
		assertEquals(command.getForm().asMap().get("followUserName").get(0), "userToFollow");
	}
}
