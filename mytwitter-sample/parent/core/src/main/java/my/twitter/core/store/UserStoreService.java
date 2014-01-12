package my.twitter.core.store;

import my.twitter.core.domain.User;

public interface UserStoreService {
	User findUserByName(String userName);
	boolean addUser(User newUser);
}
