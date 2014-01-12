package my.twitter.core.services;

import my.twitter.core.domain.User;

public interface UserService {
	User getUser(String userName);
	boolean addFollowing(User user, User followUser);
}
