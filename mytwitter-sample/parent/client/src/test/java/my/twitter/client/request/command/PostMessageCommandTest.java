package my.twitter.client.request.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import org.mockito.Mockito;

public class PostMessageCommandTest {
	@Test 
	public void test() {
		PostMessageCommand command = new PostMessageCommand("testUser","Sample test message");
		WebClient client = command.getWebClient(PostMessageCommand.PATH);
		client = Mockito.spy(client);
		Response mock = mock(Response.class);
		doReturn(mock).when(client).post(any());
		command.setClient(client);
		command.execute();
		assertEquals(client.getCurrentURI().toString(), command.getBaseUrl() + PostMessageCommand.PATH);
		assertEquals(command.getForm().asMap().get("userName").get(0), "testUser");
		assertEquals(command.getForm().asMap().get("message").get(0), "Sample test message");
	}
}
