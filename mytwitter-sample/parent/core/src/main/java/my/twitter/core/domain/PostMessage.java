package my.twitter.core.domain;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

public class PostMessage implements Comparable<PostMessage>{
	User user;
	String message;
	DateTime time;
	
	public PostMessage() {
		//for being json/over the wire friendly
	}
	
	public PostMessage(User user, String message) {
		this.user = user;
		this.message = message;
		time = new DateTime();
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public DateTime getTime() {
		return time;
	}
	public void setTime(DateTime time) {
		this.time = time;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public int compareTo(PostMessage otherMessage) {
		if(otherMessage!=null && otherMessage.getMessage().equals(getMessage()) 
				&& otherMessage.getUser().equals(getUser()) && otherMessage.getTime().equals(getTime())) {
			return 0;
		}
		return -1;
	}
	
	public String toString() {
		StringBuilder formattedMessage = new StringBuilder();
		Minutes minutesAgo = Minutes.minutesBetween(getTime(), new DateTime());
		formattedMessage.append(getUser().getUserName())
						.append(" - ")
						.append(getMessage())
						.append(" (")
						.append(minutesAgo.getMinutes())
						.append(" minutes ago)");
		
		return formattedMessage.toString();
	}
}
