package my.twitter.core.webservice;

import java.util.Set;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import my.twitter.core.domain.PostMessage;
import my.twitter.core.domain.User;
import my.twitter.core.services.MessageService;
import my.twitter.core.services.UserService;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class TwitterServiceBean implements TwitterService {
	@Resource
	private UserService userService;
	@Resource
	private MessageService messageService;
	
	@POST
	@Path("/postMessage")
	@Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded"})
	@Override
	public void postMessage(@FormParam("userName") String userName, @FormParam("message") String message) {
		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(message)) {
			throw new IllegalArgumentException("Invalid parameters passed");
		}
		User user = userService.getUser(userName);
		PostMessage postMessage = new PostMessage(user, message);
		messageService.postMessage(postMessage);
	}

	@GET
	@Produces("application/json")
	@Path("/getMessage")
	@Override
	public Set<PostMessage> getMessages(@QueryParam("userName") String userName) {
		if(StringUtils.isEmpty(userName)) {
			throw new IllegalArgumentException("Invalid parameters passed");
		}
		User user = userService.getUser(userName);
		return user.getMessages();
	}

	@GET
	@Produces("application/json")
	@Path("/getWallMessage")
	@Override
	public Set<PostMessage> getWallMessage(@QueryParam("userName") String userName) {
		if(StringUtils.isEmpty(userName)) {
			throw new IllegalArgumentException("Invalid parameters passed");
		}
		User user = userService.getUser(userName);
		return messageService.getWallMessages(user);
	}

	@POST
	@Path("/followUser")
	@Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded"})
	@Override
	public void followUser(@FormParam("userName") String userName, @FormParam("followUserName") String followUserName) {
		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(followUserName)) {
			throw new IllegalArgumentException("Invalid parameters passed");
		}
		User user = userService.getUser(userName);
		User userToFollow = userService.getUser(followUserName);
		userService.addFollowing(user, userToFollow);
	}

}
