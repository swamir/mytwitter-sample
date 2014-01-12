package my.twitter.core.services;

import java.util.Set;
import java.util.TreeSet;

import my.twitter.core.domain.PostMessage;
import my.twitter.core.domain.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceBean implements MessageService {
	private static final Log logger = LogFactory.getLog(MessageServiceBean.class);

	@Override
	public boolean postMessage(PostMessage message) {
		if(message==null) {
			throw new IllegalArgumentException(" Message invalid ");
		}
		logger.debug("Adding message " + message.getMessage() + " to user " + message.getUser().getUserName());
		message.getUser().getMessages().add(message);
		return true;
	}

	@Override
	public Set<PostMessage> getWallMessages(User user) {		
		if(user==null) {
			throw new IllegalArgumentException("Invalid user");
		}
		Set<PostMessage> wallMessages = new TreeSet<PostMessage>(new TimeComparator());
		wallMessages.addAll(user.getMessages());
		for(User followUser:user.getFollows()) {
			wallMessages.addAll(followUser.getMessages());			
		}
		return wallMessages;
	}

}
