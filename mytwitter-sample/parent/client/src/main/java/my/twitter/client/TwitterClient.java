package my.twitter.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import my.twitter.client.request.RequestParser;
import my.twitter.client.request.command.RequestCommand;

public class TwitterClient {
	static final String PROMPT = "> ";
	public static void main(String args[]) throws Exception {
		RequestParser parser = new RequestParser();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(PROMPT);
		while(true) { // get inputs endlessly
			String requestStr = reader.readLine();
			RequestCommand command = parser.parse(requestStr);
			command.execute();
			System.out.print(PROMPT);
		}
	}

}
