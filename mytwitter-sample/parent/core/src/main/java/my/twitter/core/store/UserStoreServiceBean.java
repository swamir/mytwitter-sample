package my.twitter.core.store;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.stereotype.Service;

import my.twitter.core.domain.User;

@Service
public class UserStoreServiceBean implements UserStoreService {
	//The store is a mock store
	private static List<User> users = new ArrayList<User>();
	
	@Override
	public User findUserByName(String userName) {
		User user = null;
		for(User systemUser : users) {
			if(systemUser.getUserName().equalsIgnoreCase(userName)) {
				user = systemUser;
				break;
			}
		}
		return user;
	}

	@Override
	public boolean addUser(User newUser) {
		if(newUser==null || StringUtils.isEmpty(newUser.getUserName()) || 
				users.contains(newUser)) {
			throw new IllegalArgumentException("Invalid user");
		}
		users.add(newUser);
		return true;
 	}

}
