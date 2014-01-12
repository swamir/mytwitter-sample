package my.twitter.core.services;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import my.twitter.core.domain.User;
import my.twitter.core.store.UserStoreService;

@Service
public class UserServiceBean implements UserService {
	private static final Log logger = LogFactory.getLog(UserServiceBean.class);
	
	@Resource
	private UserStoreService userStore;

	@Override
	public User getUser(String userName) {
		logger.debug("Getting the user " + userName);
		if(StringUtils.isEmpty(userName)) {
			logger.error("Invalid user name " );
			throw new IllegalArgumentException("User name is required");
		}
		userName = userName.trim();
		User user = userStore.findUserByName(userName);
		if(user==null) {
			user = new User(userName);
			userStore.addUser(user);
		}
		return user;
	}

	@Override
	public boolean addFollowing(User user, User followUser) {
		if(user==null || user.getFollows().contains(followUser)) {
			logger.error("Invalid user or user already following " + followUser );
			throw new IllegalArgumentException(" Invalid user or follow user ");
		}
		user.getFollows().add(followUser);
		return true;
	}

}
