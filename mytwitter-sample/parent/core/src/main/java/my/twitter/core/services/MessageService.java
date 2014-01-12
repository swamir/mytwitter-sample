package my.twitter.core.services;

import java.util.Set;

import my.twitter.core.domain.PostMessage;
import my.twitter.core.domain.User;

public interface MessageService {
	boolean postMessage(PostMessage message);
	Set<PostMessage> getWallMessages(User user);	
}
