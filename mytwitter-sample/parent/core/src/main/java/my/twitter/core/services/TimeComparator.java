package my.twitter.core.services;

import java.util.Comparator;

import my.twitter.core.domain.PostMessage;

public class TimeComparator implements Comparator<PostMessage> {
	@Override
	public int compare(PostMessage o1, PostMessage o2) {
		if(o1==null || o2==null) { // invalid inputs -- cannot compare
			return 0; // no sorting
		}
		if(o1.getTime().isAfter(o2.getTime())) {
			return 1;
		}
		else if(o1.getTime().isBefore(o2.getTime())) {
			return -1;
		}
		return 0;
	}
}
