package my.twitter.core.webservice;

import java.util.Set;

import my.twitter.core.domain.PostMessage;

public interface TwitterService {
	void postMessage(String userName, String message);
	Set<PostMessage> getMessages(String userName);
	Set<PostMessage> getWallMessage(String userName);
	void followUser(String userName, String followUserName);
}
