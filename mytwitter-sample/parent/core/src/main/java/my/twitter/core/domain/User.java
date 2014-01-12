package my.twitter.core.domain;

import java.util.Set;
import java.util.TreeSet;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;


public class User implements Comparable<User>{
	@JsonProperty private String userName;
	@JsonIgnore private Set<User> follows; //prevent back references in json serialization
	@JsonIgnore private Set<PostMessage> messages;
	
	public User() {
		//for being json/over the wire friendly
	}
	
	public User(String userName) {
		this.userName = userName;
		follows = new TreeSet<User>();
		messages = new TreeSet<PostMessage>();
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@JsonIgnore
	public Set<User> getFollows() {
		return follows;
	}
	@JsonIgnore 
	public void setFollows(Set<User> follows) {
		this.follows = follows;
	}
	@JsonIgnore 
	public Set<PostMessage> getMessages() {
		return messages;
	}
	@JsonIgnore 
	public void setMessage(Set<PostMessage> messages) {
		this.messages = messages;
	}
	@JsonIgnore 
	public void addMessage(PostMessage message) {
		getMessages().add(message);
	}
	
	@Override
	public int compareTo(User otherUser) { //make user name as the id.		
		if(otherUser!=null && userName.equals(otherUser.getUserName())) {
			return 0;
		}
		return -1;
	}
	
}
